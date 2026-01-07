"""
NLP Basics: Tokenization and Text Preprocessing
Demonstrates: Tokenization, stemming, lemmatization, stopword removal
"""

import re
from collections import Counter
import numpy as np

class Tokenizer:
    """
    Text Tokenization and Preprocessing
    Converts text into tokens (words or subwords)
    """
    def __init__(self, lowercase=True, remove_punctuation=True):
        self.lowercase = lowercase
        self.remove_punctuation = remove_punctuation
        self.vocab = {}
        self.word_to_idx = {}
        self.idx_to_word = {}

    def basic_tokenize(self, text):
        """
        Basic word tokenization
        Splits text into words
        """
        if self.lowercase:
            text = text.lower()

        if self.remove_punctuation:
            # Remove punctuation except apostrophes
            text = re.sub(r"[^\w\s']", '', text)

        # Split on whitespace
        tokens = text.split()

        return tokens

    def build_vocab(self, texts, min_freq=1):
        """
        Build vocabulary from texts
        Creates word-to-index and index-to-word mappings
        """
        # Count word frequencies
        word_counts = Counter()

        for text in texts:
            tokens = self.basic_tokenize(text)
            word_counts.update(tokens)

        # Filter by minimum frequency
        self.vocab = {word: count for word, count in word_counts.items()
                     if count >= min_freq}

        # Add special tokens
        special_tokens = ['<PAD>', '<UNK>', '<START>', '<END>']

        # Create mappings
        self.word_to_idx = {word: idx for idx, word in
                           enumerate(special_tokens + list(self.vocab.keys()))}
        self.idx_to_word = {idx: word for word, idx in self.word_to_idx.items()}

        print(f"✅ Vocabulary built: {len(self.word_to_idx)} tokens")

    def encode(self, text):
        """
        Convert text to sequence of indices
        """
        tokens = self.basic_tokenize(text)
        indices = [self.word_to_idx.get(token, self.word_to_idx['<UNK>'])
                  for token in tokens]
        return indices

    def decode(self, indices):
        """
        Convert indices back to text
        """
        words = [self.idx_to_word.get(idx, '<UNK>') for idx in indices]
        return ' '.join(words)

class SimpleStemmer:
    """
    Simple rule-based stemmer
    Reduces words to their root form
    """
    @staticmethod
    def stem(word):
        """
        Apply simple stemming rules
        """
        # Remove common suffixes
        suffixes = ['ing', 'ed', 'ly', 'er', 'est', 'ion', 'tion', 'ness']

        for suffix in suffixes:
            if word.endswith(suffix) and len(word) > len(suffix) + 2:
                word = word[:-len(suffix)]
                break

        return word

class StopwordRemover:
    """
    Remove common stopwords
    """
    def __init__(self):
        # Common English stopwords
        self.stopwords = {
            'the', 'a', 'an', 'and', 'or', 'but', 'in', 'on', 'at', 'to',
            'for', 'of', 'with', 'by', 'from', 'as', 'is', 'was', 'are',
            'were', 'be', 'been', 'being', 'have', 'has', 'had', 'do',
            'does', 'did', 'will', 'would', 'should', 'could', 'may',
            'might', 'must', 'can', 'this', 'that', 'these', 'those',
            'i', 'you', 'he', 'she', 'it', 'we', 'they', 'what', 'which',
            'who', 'when', 'where', 'why', 'how'
        }

    def remove(self, tokens):
        """Remove stopwords from token list"""
        return [token for token in tokens if token.lower() not in self.stopwords]

def demonstrate_tokenization():
    """Demonstrate tokenization and preprocessing"""
    print("=== NLP Tokenization & Preprocessing Demo ===\n")

    # Sample texts
    texts = [
        "Natural Language Processing is amazing!",
        "I love learning about machine learning and NLP.",
        "Tokenization is the first step in NLP pipelines.",
        "Text preprocessing helps improve model performance.",
        "Machine learning models need clean, tokenized text."
    ]

    print("📝 Sample Texts:")
    for i, text in enumerate(texts, 1):
        print(f"   {i}. {text}")

    # === Basic Tokenization ===
    print("\n1️⃣ Basic Tokenization:")
    tokenizer = Tokenizer(lowercase=True, remove_punctuation=True)

    for text in texts[:2]:
        tokens = tokenizer.basic_tokenize(text)
        print(f"   Text: {text}")
        print(f"   Tokens: {tokens}\n")

    # === Build Vocabulary ===
    print("2️⃣ Build Vocabulary:")
    tokenizer.build_vocab(texts, min_freq=1)
    print(f"   Vocabulary size: {len(tokenizer.vocab)}")
    print(f"   Sample vocab: {list(tokenizer.vocab.keys())[:10]}")

    # === Encode/Decode ===
    print("\n3️⃣ Encode & Decode:")
    test_text = "Machine learning is powerful!"
    encoded = tokenizer.encode(test_text)
    decoded = tokenizer.decode(encoded)

    print(f"   Original: {test_text}")
    print(f"   Encoded:  {encoded}")
    print(f"   Decoded:  {decoded}")

    # === Stemming ===
    print("\n4️⃣ Stemming:")
    stemmer = SimpleStemmer()
    words_to_stem = ['running', 'walked', 'quickly', 'happier',
                     'happiest', 'construction', 'happiness']

    print("   Word       →  Stem")
    print("   " + "-" * 25)
    for word in words_to_stem:
        stemmed = stemmer.stem(word)
        print(f"   {word:12} →  {stemmed}")

    # === Stopword Removal ===
    print("\n5️⃣ Stopword Removal:")
    stopword_remover = StopwordRemover()
    sample_text = "The quick brown fox jumps over the lazy dog in the park"
    tokens = tokenizer.basic_tokenize(sample_text)
    filtered_tokens = stopword_remover.remove(tokens)

    print(f"   Original:  {tokens}")
    print(f"   Filtered:  {filtered_tokens}")
    print(f"   Removed:   {set(tokens) - set(filtered_tokens)}")

    # === Complete Pipeline ===
    print("\n6️⃣ Complete Preprocessing Pipeline:")

    def preprocess_text(text):
        """Full preprocessing pipeline"""
        # Tokenize
        tokens = tokenizer.basic_tokenize(text)
        print(f"   1. Tokenized:       {tokens}")

        # Remove stopwords
        tokens = stopword_remover.remove(tokens)
        print(f"   2. Stopwords removed: {tokens}")

        # Stem
        tokens = [stemmer.stem(token) for token in tokens]
        print(f"   3. Stemmed:         {tokens}")

        # Encode
        indices = [tokenizer.word_to_idx.get(token, tokenizer.word_to_idx['<UNK>'])
                  for token in tokens]
        print(f"   4. Encoded:         {indices}")

        return tokens, indices

    test_sentence = "The students are quickly learning about natural language processing"
    print(f"\n   Input: '{test_sentence}'")
    preprocess_text(test_sentence)

    # === Tokenization Statistics ===
    print("\n📊 Tokenization Statistics:")

    all_tokens = []
    for text in texts:
        all_tokens.extend(tokenizer.basic_tokenize(text))

    token_counts = Counter(all_tokens)
    most_common = token_counts.most_common(10)

    print(f"   Total tokens: {len(all_tokens)}")
    print(f"   Unique tokens: {len(token_counts)}")
    print(f"   Avg tokens per text: {len(all_tokens) / len(texts):.1f}")
    print(f"\n   Most common tokens:")
    for token, count in most_common:
        print(f"      '{token}': {count}")

    # === Explain Concepts ===
    print("\n📚 Key Concepts:")
    print("   • Tokenization: Split text into units (words, subwords)")
    print("   • Vocabulary: Set of unique tokens in corpus")
    print("   • Encoding: Convert tokens to numerical indices")
    print("   • Stemming: Reduce words to root form (crude)")
    print("   • Lemmatization: Reduce to dictionary form (better)")
    print("   • Stopwords: Common words with little meaning")

    print("\n🎯 Tokenization Methods:")
    print("   • Word-level: Split on whitespace/punctuation")
    print("   • Character-level: Individual characters")
    print("   • Subword: BPE, WordPiece (used in BERT, GPT)")
    print("   • Sentence-level: Split into sentences")

    print("\n💡 Why Preprocessing Matters:")
    print("   ✓ Reduces vocabulary size")
    print("   ✓ Normalizes text (lowercase, punctuation)")
    print("   ✓ Removes noise (stopwords)")
    print("   ✓ Improves model efficiency")
    print("   ✓ Better generalization")

    print("\n⚙️  Modern Approaches:")
    print("   • BPE (Byte Pair Encoding): GPT models")
    print("   • WordPiece: BERT")
    print("   • SentencePiece: Language-agnostic")
    print("   • Tokenizers library: Fast implementation")

if __name__ == "__main__":
    demonstrate_tokenization()
    print("\n✅ Tokenization Demo Complete!")
