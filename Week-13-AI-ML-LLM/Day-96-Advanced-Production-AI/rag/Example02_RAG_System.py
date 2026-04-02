"""
RAG (Retrieval Augmented Generation) System
Demonstrates: Combining retrieval with generation for accurate Q&A

Real-world use: Enterprise search, documentation Q&A, knowledge bases
Critical for: Reducing hallucinations, providing source attribution
"""

import numpy as np
from collections import defaultdict
import re

class DocumentStore:
    """
    Vector database for storing and retrieving documents

    Production systems use: Pinecone, Weaviate, ChromaDB, FAISS
    """
    def __init__(self, embedding_dim=100):
        self.embedding_dim = embedding_dim
        self.documents = []
        self.embeddings = []
        self.metadata = []
        self.word_to_vec = {}

    def _simple_embedding(self, text):
        """
        Create simple word-based embedding

        Production: Use sentence-transformers, OpenAI embeddings, etc.
        """
        words = text.lower().split()

        # Create embedding as average of word vectors
        embedding = np.zeros(self.embedding_dim)
        count = 0

        for word in words:
            if word not in self.word_to_vec:
                # Create random vector for new word (in production, use pre-trained)
                self.word_to_vec[word] = np.random.randn(self.embedding_dim) * 0.01

            embedding += self.word_to_vec[word]
            count += 1

        if count > 0:
            embedding /= count

        # Normalize
        norm = np.linalg.norm(embedding)
        if norm > 0:
            embedding /= norm

        return embedding

    def add_document(self, text, metadata=None):
        """
        Add document to the store

        Args:
            text: Document content
            metadata: Additional info (source, date, author, etc.)
        """
        embedding = self._simple_embedding(text)

        self.documents.append(text)
        self.embeddings.append(embedding)
        self.metadata.append(metadata or {})

        return len(self.documents) - 1  # Document ID

    def retrieve(self, query, top_k=3):
        """
        Retrieve most relevant documents

        Uses cosine similarity for ranking
        """
        query_embedding = self._simple_embedding(query)

        # Calculate similarities
        similarities = []
        for idx, doc_embedding in enumerate(self.embeddings):
            similarity = np.dot(query_embedding, doc_embedding)
            similarities.append((idx, similarity))

        # Sort by similarity (descending)
        similarities.sort(key=lambda x: x[1], reverse=True)

        # Return top-k results
        results = []
        for idx, score in similarities[:top_k]:
            results.append({
                'document': self.documents[idx],
                'score': float(score),
                'metadata': self.metadata[idx],
                'id': idx
            })

        return results

class SimpleRAG:
    """
    RAG (Retrieval Augmented Generation) System

    Workflow:
    1. User asks question
    2. Retrieve relevant documents from knowledge base
    3. Augment prompt with retrieved context
    4. Generate answer using LLM

    Benefits:
    - Reduces hallucinations
    - Grounds answers in facts
    - Provides source attribution
    - Can update knowledge without retraining
    """
    def __init__(self):
        self.document_store = DocumentStore()
        self.conversation_history = []

    def add_knowledge(self, documents_with_metadata):
        """
        Add documents to knowledge base

        Args:
            documents_with_metadata: List of (text, metadata) tuples
        """
        for text, metadata in documents_with_metadata:
            self.document_store.add_document(text, metadata)

        print(f"✅ Added {len(documents_with_metadata)} documents to knowledge base")

    def generate_answer(self, question, retrieved_docs):
        """
        Generate answer based on retrieved documents

        In production: Call GPT-4, Claude, or other LLM API
        Here: Simple template-based generation for demonstration
        """
        # Extract key information from retrieved docs
        context = "\n\n".join([doc['document'] for doc in retrieved_docs])

        # Simple answer generation (in production, use LLM)
        answer = self._extract_answer(question, context, retrieved_docs)

        return answer

    def _extract_answer(self, question, context, retrieved_docs):
        """
        Simple answer extraction

        Production: Use LLM with prompt engineering
        """
        # Find most relevant sentence
        question_words = set(question.lower().split())

        best_sentence = ""
        best_score = 0

        for doc in retrieved_docs:
            sentences = doc['document'].split('.')
            for sentence in sentences:
                sentence_words = set(sentence.lower().split())
                overlap = len(question_words & sentence_words)

                if overlap > best_score:
                    best_score = overlap
                    best_sentence = sentence.strip()

        # Format answer with sources
        answer = {
            'answer': best_sentence if best_sentence else "I don't have enough information to answer that question.",
            'sources': retrieved_docs,
            'confidence': retrieved_docs[0]['score'] if retrieved_docs else 0.0
        }

        return answer

    def ask(self, question, top_k=3):
        """
        Main RAG pipeline: Retrieve → Augment → Generate

        Args:
            question: User's question
            top_k: Number of documents to retrieve

        Returns:
            Answer with sources
        """
        print(f"\n❓ Question: {question}")

        # Step 1: Retrieve relevant documents
        print(f"\n🔍 Step 1: Retrieving relevant documents...")
        retrieved_docs = self.document_store.retrieve(question, top_k=top_k)

        if not retrieved_docs:
            return {
                'answer': "No relevant information found in knowledge base.",
                'sources': [],
                'confidence': 0.0
            }

        print(f"   Found {len(retrieved_docs)} relevant documents")
        for i, doc in enumerate(retrieved_docs, 1):
            print(f"   {i}. Score: {doc['score']:.3f} | Source: {doc['metadata'].get('source', 'Unknown')}")

        # Step 2: Augment prompt with context
        print(f"\n📝 Step 2: Augmenting context...")

        # Step 3: Generate answer
        print(f"\n🤖 Step 3: Generating answer...")
        answer = self.generate_answer(question, retrieved_docs)

        # Store in conversation history
        self.conversation_history.append({
            'question': question,
            'answer': answer
        })

        return answer

def demonstrate_rag_system():
    """
    Demonstrate RAG system with enterprise knowledge base
    """
    print("=" * 80)
    print("🚀 RAG (RETRIEVAL AUGMENTED GENERATION) SYSTEM DEMO")
    print("=" * 80)

    # Initialize RAG system
    rag = SimpleRAG()

    # Create knowledge base (simulating company documentation)
    print("\n📚 Building Knowledge Base...")

    knowledge_base = [
        (
            "Our company offers three pricing tiers: Basic at $29/month with 100 API calls, "
            "Professional at $99/month with 1000 API calls, and Enterprise at $499/month with unlimited calls.",
            {'source': 'pricing_docs.md', 'section': 'pricing', 'date': '2024-01-15'}
        ),
        (
            "To integrate our API, first obtain an API key from your dashboard. "
            "Then include the header 'Authorization: Bearer YOUR_API_KEY' in all requests. "
            "The base URL is https://api.example.com/v1.",
            {'source': 'api_guide.md', 'section': 'authentication', 'date': '2024-01-20'}
        ),
        (
            "Our service level agreement (SLA) guarantees 99.9% uptime for Enterprise customers. "
            "We provide 24/7 support and respond to critical issues within 1 hour.",
            {'source': 'sla_agreement.pdf', 'section': 'guarantees', 'date': '2024-01-10'}
        ),
        (
            "Data retention policy: We store customer data for 90 days in active storage. "
            "After that, data is archived for 7 years for compliance purposes. "
            "Customers can request immediate deletion at any time.",
            {'source': 'privacy_policy.md', 'section': 'data_retention', 'date': '2024-01-05'}
        ),
        (
            "Our machine learning models are retrained weekly using the latest data. "
            "Model accuracy is monitored continuously and customers are notified of significant changes. "
            "We support custom model fine-tuning for Enterprise customers.",
            {'source': 'ml_operations.md', 'section': 'model_management', 'date': '2024-01-25'}
        ),
        (
            "Security measures include end-to-end encryption, SOC 2 Type II compliance, "
            "regular penetration testing, and role-based access control (RBAC). "
            "All data is encrypted at rest using AES-256.",
            {'source': 'security_whitepaper.pdf', 'section': 'security', 'date': '2024-01-12'}
        ),
        (
            "API rate limits: Basic tier allows 100 requests per minute, "
            "Professional tier allows 1000 requests per minute, "
            "Enterprise tier has custom limits based on agreement.",
            {'source': 'api_limits.md', 'section': 'rate_limiting', 'date': '2024-01-18'}
        )
    ]

    rag.add_knowledge(knowledge_base)

    # Test questions
    print("\n" + "=" * 80)
    print("📋 TESTING RAG SYSTEM")
    print("=" * 80)

    test_questions = [
        "What are the pricing tiers?",
        "How do I authenticate with the API?",
        "What is the uptime guarantee?",
        "How long is customer data stored?",
        "What security measures are in place?"
    ]

    results = []

    for question in test_questions:
        answer = rag.ask(question, top_k=2)
        results.append((question, answer))

        print(f"\n💡 Answer: {answer['answer']}")
        print(f"   Confidence: {answer['confidence']:.3f}")
        print(f"   Number of sources: {len(answer['sources'])}")
        print("-" * 80)

    # Visualize RAG architecture
    print("\n" + "=" * 80)
    print("📊 RAG SYSTEM ARCHITECTURE")
    print("=" * 80)

    print("""
╔══════════════════════════════════════════════════════════════════════╗
║                    RAG SYSTEM ARCHITECTURE                           ║
╠══════════════════════════════════════════════════════════════════════╣
║                                                                       ║
║  USER QUESTION: "What are the pricing tiers?"                        ║
║       ↓                                                               ║
║  ┌─────────────────────────────────────────────────────┐            ║
║  │  STEP 1: RETRIEVAL                                  │            ║
║  │  ────────────────                                   │            ║
║  │                                                      │            ║
║  │  1. Convert question to embedding vector            │            ║
║  │     "pricing tiers" → [0.2, 0.5, -0.1, ...]        │            ║
║  │                                                      │            ║
║  │  2. Search vector database                          │            ║
║  │     ┌──────────────────────────────┐               │            ║
║  │     │  Vector Database (FAISS)     │               │            ║
║  │     │  ├── Doc1: [0.3, 0.4, ...]   │               │            ║
║  │     │  ├── Doc2: [0.1, 0.6, ...]   │               │            ║
║  │     │  ├── Doc3: [0.2, 0.5, ...]  ← Match!        │            ║
║  │     │  └── Doc4: [0.8, 0.1, ...]   │               │            ║
║  │     └──────────────────────────────┘               │            ║
║  │                                                      │            ║
║  │  3. Rank by similarity (cosine similarity)          │            ║
║  │     • Doc3: 0.89 ⭐                                 │            ║
║  │     • Doc7: 0.76                                    │            ║
║  │     • Doc1: 0.65                                    │            ║
║  └─────────────────────────────────────────────────────┘            ║
║       ↓                                                               ║
║  ┌─────────────────────────────────────────────────────┐            ║
║  │  STEP 2: AUGMENTATION                               │            ║
║  │  ─────────────────                                  │            ║
║  │                                                      │            ║
║  │  Create enriched prompt:                            │            ║
║  │                                                      │            ║
║  │  ┌────────────────────────────────────────┐        │            ║
║  │  │ Context from retrieved documents:      │        │            ║
║  │  │                                         │        │            ║
║  │  │ [Doc3] "Our company offers three       │        │            ║
║  │  │  pricing tiers: Basic at $29/month..." │        │            ║
║  │  │                                         │        │            ║
║  │  │ [Doc7] "API rate limits: Basic tier    │        │            ║
║  │  │  allows 100 requests per minute..."    │        │            ║
║  │  │                                         │        │            ║
║  │  │ Question: What are the pricing tiers?  │        │            ║
║  │  │                                         │        │            ║
║  │  │ Answer based on the context above:     │        │            ║
║  │  └────────────────────────────────────────┘        │            ║
║  └─────────────────────────────────────────────────────┘            ║
║       ↓                                                               ║
║  ┌─────────────────────────────────────────────────────┐            ║
║  │  STEP 3: GENERATION                                 │            ║
║  │  ───────────────                                    │            ║
║  │                                                      │            ║
║  │  Send augmented prompt to LLM (GPT-4, Claude)       │            ║
║  │                                                      │            ║
║  │  LLM generates answer with:                         │            ║
║  │  ✓ Factual accuracy (grounded in docs)             │            ║
║  │  ✓ Source attribution                               │            ║
║  │  ✓ Reduced hallucinations                           │            ║
║  │                                                      │            ║
║  │  Output: "We offer three tiers: Basic ($29/mo),    │            ║
║  │          Professional ($99/mo), and Enterprise..."  │            ║
║  │          [Source: pricing_docs.md]                  │            ║
║  └─────────────────────────────────────────────────────┘            ║
║       ↓                                                               ║
║  FINAL ANSWER TO USER ✅                                             ║
║                                                                       ║
╚══════════════════════════════════════════════════════════════════════╝
    """)

    print("\n" + "=" * 80)
    print("🎯 RAG VS STANDARD LLM")
    print("=" * 80)

    print("""
┌─────────────────────────────────────────────────────────────────────┐
│                 STANDARD LLM      vs      RAG SYSTEM                │
├─────────────────────────────────────────────────────────────────────┤
│                                                                      │
│  Knowledge Source:                                                  │
│    ❌ Training data only          ✅ External knowledge base        │
│    ❌ Static (frozen)             ✅ Dynamic (updatable)            │
│                                                                      │
│  Accuracy:                                                           │
│    ❌ May hallucinate             ✅ Grounded in facts              │
│    ❌ No source attribution       ✅ Cites sources                  │
│                                                                      │
│  Freshness:                                                          │
│    ❌ Outdated information        ✅ Real-time updates              │
│    ❌ Need retraining             ✅ Just update docs               │
│                                                                      │
│  Domain Specific:                                                    │
│    ❌ General knowledge           ✅ Custom knowledge               │
│    ❌ May lack details            ✅ Company-specific info          │
│                                                                      │
│  Compliance:                                                         │
│    ❌ Hard to verify              ✅ Traceable sources              │
│    ❌ No audit trail              ✅ Full audit trail               │
│                                                                      │
└─────────────────────────────────────────────────────────────────────┘
    """)

    print("\n💡 Production RAG Best Practices:")
    print("\n1️⃣  DOCUMENT PREPARATION:")
    print("   • Chunk documents appropriately (200-500 tokens)")
    print("   • Maintain metadata (source, date, author)")
    print("   • Clean and preprocess text")
    print("   • Handle different formats (PDF, MD, HTML)")

    print("\n2️⃣  EMBEDDING STRATEGY:")
    print("   • Use high-quality embeddings (OpenAI, Cohere)")
    print("   • Consider domain-specific embeddings")
    print("   • Cache embeddings for efficiency")
    print("   • Update embeddings when docs change")

    print("\n3️⃣  RETRIEVAL OPTIMIZATION:")
    print("   • Experiment with top-k values")
    print("   • Use hybrid search (dense + sparse)")
    print("   • Implement re-ranking")
    print("   • Consider query expansion")

    print("\n4️⃣  GENERATION QUALITY:")
    print("   • Craft effective prompts")
    print("   • Include instructions for citation")
    print("   • Set appropriate temperature")
    print("   • Implement answer validation")

    print("\n5️⃣  MONITORING & EVALUATION:")
    print("   • Track retrieval accuracy")
    print("   • Monitor answer quality")
    print("   • Collect user feedback")
    print("   • A/B test different strategies")

    print("\n🏭 Production RAG Stack:")
    print("   • Vector DB: Pinecone, Weaviate, ChromaDB, FAISS")
    print("   • Embeddings: OpenAI, Cohere, sentence-transformers")
    print("   • LLM: GPT-4, Claude, Llama 2")
    print("   • Framework: LangChain, LlamaIndex")
    print("   • Monitoring: Weights & Biases, Arize AI")

    print("\n🎯 Real-World RAG Applications:")
    print("   📚 Enterprise Knowledge Search")
    print("   🏥 Medical Documentation Q&A")
    print("   ⚖️  Legal Document Analysis")
    print("   📊 Financial Report Analysis")
    print("   🛠️  Technical Support Chatbots")
    print("   📖 Educational Content Q&A")
    print("   🏢 HR Policy Assistant")
    print("   💼 Sales Enablement Tools")

    print("\n⚠️  Common Pitfalls to Avoid:")
    print("   ✗ Poor document chunking (too large/small)")
    print("   ✗ Irrelevant retrievals (low similarity threshold)")
    print("   ✗ Context window overflow (too many docs)")
    print("   ✗ No source attribution (trust issues)")
    print("   ✗ Stale knowledge base (outdated docs)")
    print("   ✗ No fallback for low confidence")

if __name__ == "__main__":
    np.random.seed(42)  # For reproducibility
    demonstrate_rag_system()
    print("\n" + "=" * 80)
    print("✅ RAG SYSTEM DEMO COMPLETE!")
    print("=" * 80)
