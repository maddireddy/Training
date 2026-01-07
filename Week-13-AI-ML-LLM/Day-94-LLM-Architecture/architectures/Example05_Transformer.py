"""
Transformer Architecture Visualization
Demonstrates: Attention mechanism, transformer components
"""

import numpy as np
import matplotlib.pyplot as plt

class MultiHeadAttention:
    """
    Simplified Multi-Head Attention Mechanism
    Core component of Transformer architecture
    """
    def __init__(self, d_model, num_heads):
        self.d_model = d_model
        self.num_heads = num_heads
        self.d_k = d_model // num_heads

        # Initialize weight matrices
        self.W_q = np.random.randn(d_model, d_model) * 0.01
        self.W_k = np.random.randn(d_model, d_model) * 0.01
        self.W_v = np.random.randn(d_model, d_model) * 0.01
        self.W_o = np.random.randn(d_model, d_model) * 0.01

    def scaled_dot_product_attention(self, Q, K, V):
        """
        Attention(Q, K, V) = softmax(QK^T / √d_k)V

        Q: Query matrix
        K: Key matrix
        V: Value matrix
        """
        # Calculate attention scores
        scores = np.dot(Q, K.T) / np.sqrt(self.d_k)

        # Apply softmax
        attention_weights = self.softmax(scores)

        # Apply attention to values
        output = np.dot(attention_weights, V)

        return output, attention_weights

    def softmax(self, x):
        """Softmax activation"""
        exp_x = np.exp(x - np.max(x, axis=-1, keepdims=True))
        return exp_x / np.sum(exp_x, axis=-1, keepdims=True)

    def forward(self, x):
        """
        Apply multi-head attention
        """
        # Linear projections
        Q = np.dot(x, self.W_q)
        K = np.dot(x, self.W_k)
        V = np.dot(x, self.W_v)

        # Apply attention
        output, attention_weights = self.scaled_dot_product_attention(Q, K, V)

        # Final linear projection
        output = np.dot(output, self.W_o)

        return output, attention_weights

def visualize_attention():
    """
    Visualize how attention mechanism works
    """
    print("=== Transformer Attention Visualization ===\n")

    # Example: Attention in a sentence
    sentence = ["The", "cat", "sat", "on", "the", "mat"]
    seq_len = len(sentence)

    # Create sample attention matrix
    np.random.seed(42)
    attention_matrix = np.random.rand(seq_len, seq_len)

    # Normalize to sum to 1 (softmax-like)
    attention_matrix = attention_matrix / attention_matrix.sum(axis=1, keepdims=True)

    # Make it focus on relevant words (manual for demo)
    attention_matrix[1, 2] = 0.7  # "cat" attends to "sat"
    attention_matrix[2, 1] = 0.6  # "sat" attends to "cat"
    attention_matrix[2, 5] = 0.3  # "sat" attends to "mat"

    # Re-normalize
    attention_matrix = attention_matrix / attention_matrix.sum(axis=1, keepdims=True)

    # Visualize
    plt.figure(figsize=(10, 8))
    plt.imshow(attention_matrix, cmap='YlOrRd', aspect='auto')
    plt.colorbar(label='Attention Weight')
    plt.xticks(range(seq_len), sentence, rotation=45)
    plt.yticks(range(seq_len), sentence)
    plt.xlabel('Keys/Values')
    plt.ylabel('Queries')
    plt.title('Self-Attention Heatmap')

    # Add values to cells
    for i in range(seq_len):
        for j in range(seq_len):
            plt.text(j, i, f'{attention_matrix[i, j]:.2f}',
                    ha='center', va='center',
                    color='white' if attention_matrix[i, j] > 0.5 else 'black')

    plt.tight_layout()
    plt.savefig('attention_mechanism.png')
    print("📊 Attention visualization saved to 'attention_mechanism.png'")

    # Print explanation
    print("\n📚 Understanding Attention:")
    print("   Each row shows what a word 'attends to'")
    print("   Higher values = stronger attention")
    print("   Example: 'cat' strongly attends to 'sat' (0.70)")

def demonstrate_transformer_architecture():
    """
    Show transformer architecture components
    """
    print("\n=== Transformer Architecture ===\n")

    architecture = """
╔═══════════════════════════════════════════════════════╗
║           TRANSFORMER ARCHITECTURE                     ║
╠═══════════════════════════════════════════════════════╣
║                                                        ║
║  INPUT: "The cat sat on the mat"                      ║
║    ↓                                                   ║
║  ┌────────────────────────────────────┐               ║
║  │  Token Embeddings (512-dim)        │               ║
║  │  + Positional Encoding             │               ║
║  └────────────────────────────────────┘               ║
║    ↓                                                   ║
║  ┌────────────────────────────────────┐               ║
║  │  Multi-Head Attention (8 heads)    │  ◄─┐         ║
║  │  - Query, Key, Value               │    │         ║
║  │  - Attention(Q,K,V) = softmax(...) │    │         ║
║  └────────────────────────────────────┘    │         ║
║    ↓                                        │         ║
║  Add & Normalize  ─────────────────────────┘         ║
║    ↓                                                   ║
║  ┌────────────────────────────────────┐               ║
║  │  Feed Forward Network              │  ◄─┐         ║
║  │  - Linear (512 → 2048)             │    │         ║
║  │  - ReLU                            │    │         ║
║  │  - Linear (2048 → 512)             │    │         ║
║  └────────────────────────────────────┘    │         ║
║    ↓                                        │         ║
║  Add & Normalize  ─────────────────────────┘         ║
║    ↓                                                   ║
║  [Repeat N times (e.g., 6 layers)]                   ║
║    ↓                                                   ║
║  ┌────────────────────────────────────┐               ║
║  │  Output Linear + Softmax           │               ║
║  └────────────────────────────────────┘               ║
║    ↓                                                   ║
║  OUTPUT: Predictions                                  ║
║                                                        ║
╚═══════════════════════════════════════════════════════╝
    """

    print(architecture)

    print("\n🔑 Key Components:")
    print("   1. Self-Attention: Words attend to other words")
    print("   2. Multi-Head: Multiple attention patterns (8 heads)")
    print("   3. Feed Forward: Non-linear transformations")
    print("   4. Residual Connections: Add & Normalize")
    print("   5. Layer Normalization: Stable training")

    print("\n📊 Typical Parameters:")
    print("   • d_model: 512 (embedding dimension)")
    print("   • num_heads: 8 (attention heads)")
    print("   • d_ff: 2048 (feed-forward dimension)")
    print("   • num_layers: 6 (encoder/decoder layers)")
    print("   • vocab_size: 30000+ (token vocabulary)")

    print("\n🎯 Applications:")
    print("   ✓ Machine Translation (e.g., English → French)")
    print("   ✓ Text Generation (e.g., GPT models)")
    print("   ✓ Question Answering (e.g., BERT)")
    print("   ✓ Code Generation (e.g., Codex)")
    print("   ✓ Image Understanding (e.g., Vision Transformers)")

if __name__ == "__main__":
    # Create a simple attention example
    d_model = 8
    num_heads = 2
    seq_len = 4

    # Sample input
    x = np.random.randn(seq_len, d_model)

    # Create attention layer
    attention = MultiHeadAttention(d_model, num_heads)

    # Forward pass
    output, attention_weights = attention.forward(x)

    print("Attention Mechanism Example:")
    print(f"Input shape: {x.shape}")
    print(f"Output shape: {output.shape}")
    print(f"Attention weights shape: {attention_weights.shape}\n")

    # Visualize attention for a sentence
    visualize_attention()

    # Show full architecture
    demonstrate_transformer_architecture()

    print("\n✅ Transformer Architecture Demo Complete!")
