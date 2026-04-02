"""
Word Embeddings
Demonstrates: Word2Vec concepts, embedding space, semantic relationships
"""

import numpy as np
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA

class SimpleWord2Vec:
    """
    Simplified Word2Vec Implementation (Skip-gram)
    Maps words to dense vector representations
    """
    def __init__(self, embedding_dim=50, window_size=2, learning_rate=0.01):
        """
        Parameters:
        embedding_dim: Dimension of word vectors
        window_size: Context window size
        learning_rate: Learning rate for training
        """
        self.embedding_dim = embedding_dim
        self.window_size = window_size
        self.learning_rate = learning_rate

        self.vocab = {}
        self.word_to_idx = {}
        self.idx_to_word = {}

        # Embeddings (will be initialized after vocab is built)
        self.W_input = None   # Input word embeddings
        self.W_output = None  # Output context embeddings

    def build_vocab(self, sentences):
        """Build vocabulary from sentences"""
        words = []
        for sentence in sentences:
            words.extend(sentence.lower().split())

        unique_words = sorted(set(words))

        self.word_to_idx = {word: idx for idx, word in enumerate(unique_words)}
        self.idx_to_word = {idx: word for word, idx in self.word_to_idx.items()}
        self.vocab = {word: words.count(word) for word in unique_words}

        vocab_size = len(self.word_to_idx)

        # Initialize embeddings
        self.W_input = np.random.randn(vocab_size, self.embedding_dim) * 0.01
        self.W_output = np.random.randn(self.embedding_dim, vocab_size) * 0.01

        print(f"✅ Vocabulary: {vocab_size} words")

    def generate_training_data(self, sentences):
        """
        Generate (target, context) pairs for Skip-gram

        Skip-gram: Predict context words from target word
        Example: "the cat sat on mat"
                 target="sat" → context=["the", "cat", "on", "mat"]
        """
        training_data = []

        for sentence in sentences:
            words = sentence.lower().split()
            indices = [self.word_to_idx[word] for word in words]

            for i, target_idx in enumerate(indices):
                # Get context words within window
                start = max(0, i - self.window_size)
                end = min(len(indices), i + self.window_size + 1)

                for j in range(start, end):
                    if i != j:  # Don't include the target word itself
                        context_idx = indices[j]
                        training_data.append((target_idx, context_idx))

        return training_data

    def softmax(self, x):
        """Softmax activation"""
        exp_x = np.exp(x - np.max(x))
        return exp_x / np.sum(exp_x)

    def train(self, sentences, epochs=100):
        """
        Train Word2Vec embeddings

        Skip-gram objective:
        Maximize P(context | target)
        """
        training_data = self.generate_training_data(sentences)
        n_samples = len(training_data)

        print(f"Training on {n_samples} word pairs...")

        for epoch in range(epochs):
            total_loss = 0

            for target_idx, context_idx in training_data:
                # Forward pass
                # Get target word embedding
                h = self.W_input[target_idx]  # (embedding_dim,)

                # Compute output scores
                u = np.dot(h, self.W_output)  # (vocab_size,)

                # Softmax
                y_pred = self.softmax(u)

                # Loss (negative log likelihood)
                loss = -np.log(y_pred[context_idx] + 1e-10)
                total_loss += loss

                # Backward pass
                # Output layer gradient
                dy = y_pred.copy()
                dy[context_idx] -= 1

                # Gradients
                dW_output = np.outer(h, dy)
                dW_input = np.dot(self.W_output, dy)

                # Update weights
                self.W_output -= self.learning_rate * dW_output
                self.W_input[target_idx] -= self.learning_rate * dW_input

            if (epoch + 1) % 20 == 0:
                avg_loss = total_loss / n_samples
                print(f"  Epoch {epoch + 1}: Loss = {avg_loss:.4f}")

    def get_word_vector(self, word):
        """Get embedding vector for a word"""
        if word in self.word_to_idx:
            idx = self.word_to_idx[word]
            return self.W_input[idx]
        return None

    def cosine_similarity(self, vec1, vec2):
        """Compute cosine similarity between two vectors"""
        dot_product = np.dot(vec1, vec2)
        norm1 = np.linalg.norm(vec1)
        norm2 = np.linalg.norm(vec2)
        return dot_product / (norm1 * norm2 + 1e-10)

    def most_similar(self, word, top_k=5):
        """Find most similar words to given word"""
        if word not in self.word_to_idx:
            return []

        word_vec = self.get_word_vector(word)
        similarities = []

        for other_word in self.word_to_idx:
            if other_word != word:
                other_vec = self.get_word_vector(other_word)
                sim = self.cosine_similarity(word_vec, other_vec)
                similarities.append((other_word, sim))

        similarities.sort(key=lambda x: x[1], reverse=True)
        return similarities[:top_k]

    def analogy(self, word_a, word_b, word_c):
        """
        Solve word analogy: word_a is to word_b as word_c is to ?
        Example: "king" is to "queen" as "man" is to "woman"

        Formula: vec(word_b) - vec(word_a) + vec(word_c) ≈ vec(word_d)
        """
        if (word_a not in self.word_to_idx or
            word_b not in self.word_to_idx or
            word_c not in self.word_to_idx):
            return None

        vec_a = self.get_word_vector(word_a)
        vec_b = self.get_word_vector(word_b)
        vec_c = self.get_word_vector(word_c)

        # Compute target vector
        vec_target = vec_b - vec_a + vec_c

        # Find closest word
        best_word = None
        best_sim = -float('inf')

        for word in self.word_to_idx:
            if word not in [word_a, word_b, word_c]:
                vec = self.get_word_vector(word)
                sim = self.cosine_similarity(vec_target, vec)
                if sim > best_sim:
                    best_sim = sim
                    best_word = word

        return best_word, best_sim

def demonstrate_word_embeddings():
    """Demonstrate word embeddings"""
    print("=== Word Embeddings (Word2Vec) Demo ===\n")

    # Sample corpus
    sentences = [
        "the king loves the queen",
        "the queen loves the king",
        "the prince is the son of the king",
        "the princess is the daughter of the queen",
        "the dog runs in the park",
        "the cat sleeps in the house",
        "the boy plays with the dog",
        "the girl plays with the cat",
        "man and woman are humans",
        "king and queen are royalty",
        "prince and princess are children",
        "cat and dog are animals"
    ]

    print("📝 Training Corpus:")
    for i, sent in enumerate(sentences[:5], 1):
        print(f"   {i}. {sent}")
    print(f"   ... ({len(sentences)} sentences total)")

    # Train Word2Vec
    w2v = SimpleWord2Vec(embedding_dim=20, window_size=2, learning_rate=0.05)
    w2v.build_vocab(sentences)

    print("\nTraining Word2Vec...")
    w2v.train(sentences, epochs=200)

    # === Semantic Similarity ===
    print("\n1️⃣ Semantic Similarity:")
    test_words = ['king', 'queen', 'dog', 'cat']

    for word in test_words:
        similar = w2v.most_similar(word, top_k=3)
        print(f"   Words similar to '{word}':")
        for sim_word, score in similar:
            print(f"      {sim_word}: {score:.3f}")

    # === Word Analogies ===
    print("\n2️⃣ Word Analogies:")
    analogies = [
        ('king', 'queen', 'prince'),
        ('man', 'woman', 'boy'),
        ('dog', 'cat', 'boy')
    ]

    for word_a, word_b, word_c in analogies:
        result = w2v.analogy(word_a, word_b, word_c)
        if result:
            word_d, score = result
            print(f"   '{word_a}' : '{word_b}' :: '{word_c}' : '{word_d}' (score: {score:.3f})")

    # === Visualize Embeddings ===
    print("\n3️⃣ Visualizing Embeddings (PCA):")

    # Get all word vectors
    words = list(w2v.word_to_idx.keys())
    vectors = np.array([w2v.get_word_vector(word) for word in words])

    # Reduce to 2D using PCA
    pca = PCA(n_components=2)
    vectors_2d = pca.fit_transform(vectors)

    # Plot
    plt.figure(figsize=(12, 8))

    # Color code by category
    categories = {
        'royalty': ['king', 'queen', 'prince', 'princess'],
        'gender': ['man', 'woman', 'boy', 'girl'],
        'animals': ['dog', 'cat'],
        'places': ['park', 'house']
    }

    colors = {'royalty': 'red', 'gender': 'blue', 'animals': 'green', 'places': 'orange'}

    # Plot all points
    plt.scatter(vectors_2d[:, 0], vectors_2d[:, 1], alpha=0.3, c='gray', s=20)

    # Plot and label important words
    for category, word_list in categories.items():
        for word in word_list:
            if word in w2v.word_to_idx:
                idx = words.index(word)
                x, y = vectors_2d[idx]
                plt.scatter(x, y, c=colors[category], s=100, alpha=0.6)
                plt.annotate(word, (x, y), fontsize=10, weight='bold')

    # Add legend
    for category, color in colors.items():
        plt.scatter([], [], c=color, label=category.capitalize(), s=100)

    plt.xlabel('PCA Component 1')
    plt.ylabel('PCA Component 2')
    plt.title('Word Embeddings Visualization (2D PCA)')
    plt.legend()
    plt.grid(True, alpha=0.3)
    plt.tight_layout()
    plt.savefig('word_embeddings_visualization.png')
    print("   📊 Visualization saved to 'word_embeddings_visualization.png'")

    # === Explain Concepts ===
    print("\n📚 Word Embeddings Concepts:")
    print("   • Dense Representation: Words → low-dim vectors")
    print("   • Semantic Similarity: Similar words → similar vectors")
    print("   • Distributional Semantics: 'Words by company they keep'")
    print("   • Vector Arithmetic: king - man + woman ≈ queen")
    print("   • Skip-gram: Predict context from target word")
    print("   • CBOW: Predict target from context words")

    print("\n🎯 Word2Vec Models:")
    print("   • Skip-gram: Better for rare words")
    print("   • CBOW (Continuous Bag of Words): Faster")
    print("   • Negative Sampling: Efficient training")
    print("   • Hierarchical Softmax: Alternative output layer")

    print("\n💡 Applications:")
    print("   • Sentiment Analysis")
    print("   • Text Classification")
    print("   • Machine Translation")
    print("   • Information Retrieval")
    print("   • Recommendation Systems")

    print("\n⚙️  Modern Alternatives:")
    print("   • GloVe: Global vectors for word representation")
    print("   • FastText: Subword embeddings (handles OOV)")
    print("   • ELMo: Contextual embeddings")
    print("   • BERT: Bidirectional contextual embeddings")
    print("   • GPT: Unidirectional contextual embeddings")

    print("\n🔍 Properties of Good Embeddings:")
    print("   ✓ Capture semantic relationships")
    print("   ✓ Dimensionality reduction (sparse → dense)")
    print("   ✓ Transfer learning (pre-trained)")
    print("   ✓ Compositionality (combine meanings)")

if __name__ == "__main__":
    demonstrate_word_embeddings()
    print("\n✅ Word Embeddings Demo Complete!")
