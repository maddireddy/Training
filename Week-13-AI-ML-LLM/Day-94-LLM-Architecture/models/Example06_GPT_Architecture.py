"""
GPT (Generative Pre-trained Transformer) Architecture
Demonstrates: Decoder-only transformer, autoregressive generation
"""

import numpy as np
import matplotlib.pyplot as plt

class GPTBlock:
    """
    Single GPT Transformer Block
    Components: Masked Self-Attention + Feed Forward + Layer Norm
    """
    def __init__(self, d_model, n_heads, d_ff):
        """
        Parameters:
        d_model: Model dimension (e.g., 768)
        n_heads: Number of attention heads (e.g., 12)
        d_ff: Feed-forward dimension (e.g., 3072)
        """
        self.d_model = d_model
        self.n_heads = n_heads
        self.d_ff = d_ff
        self.d_k = d_model // n_heads

        # Multi-head attention weights
        self.W_q = np.random.randn(d_model, d_model) * 0.01
        self.W_k = np.random.randn(d_model, d_model) * 0.01
        self.W_v = np.random.randn(d_model, d_model) * 0.01
        self.W_o = np.random.randn(d_model, d_model) * 0.01

        # Feed-forward weights
        self.W_ff1 = np.random.randn(d_model, d_ff) * 0.01
        self.b_ff1 = np.zeros(d_ff)
        self.W_ff2 = np.random.randn(d_ff, d_model) * 0.01
        self.b_ff2 = np.zeros(d_model)

    def layer_norm(self, x, eps=1e-6):
        """Layer Normalization"""
        mean = np.mean(x, axis=-1, keepdims=True)
        std = np.std(x, axis=-1, keepdims=True)
        return (x - mean) / (std + eps)

    def masked_attention(self, Q, K, V):
        """
        Masked Self-Attention (Causal Attention)

        Prevents positions from attending to future positions
        Essential for autoregressive generation

        Mask pattern (for sequence length 4):
        [[1, 0, 0, 0],
         [1, 1, 0, 0],
         [1, 1, 1, 0],
         [1, 1, 1, 1]]
        """
        seq_len = Q.shape[0]

        # Compute attention scores
        scores = np.dot(Q, K.T) / np.sqrt(self.d_k)

        # Create causal mask (lower triangular)
        mask = np.tril(np.ones((seq_len, seq_len)))

        # Apply mask (set future positions to -inf)
        scores = scores * mask + (1 - mask) * (-1e10)

        # Softmax
        attention_weights = self.softmax(scores)

        # Apply attention to values
        output = np.dot(attention_weights, V)

        return output, attention_weights

    def softmax(self, x):
        """Softmax activation"""
        exp_x = np.exp(x - np.max(x, axis=-1, keepdims=True))
        return exp_x / np.sum(exp_x, axis=-1, keepdims=True)

    def gelu(self, x):
        """
        GELU activation (Gaussian Error Linear Unit)
        Used in GPT instead of ReLU

        GELU(x) ≈ 0.5 * x * (1 + tanh(√(2/π) * (x + 0.044715 * x³)))
        """
        return 0.5 * x * (1 + np.tanh(np.sqrt(2 / np.pi) * (x + 0.044715 * x**3)))

    def forward(self, x):
        """
        Forward pass through GPT block

        Architecture:
        1. Masked Multi-Head Self-Attention
        2. Add & Norm (Residual Connection)
        3. Feed Forward Network
        4. Add & Norm (Residual Connection)
        """
        # === Masked Self-Attention ===
        # Linear projections
        Q = np.dot(x, self.W_q)
        K = np.dot(x, self.W_k)
        V = np.dot(x, self.W_v)

        # Apply masked attention
        attn_output, attn_weights = self.masked_attention(Q, K, V)

        # Output projection
        attn_output = np.dot(attn_output, self.W_o)

        # Add & Norm (Residual Connection)
        x = self.layer_norm(x + attn_output)

        # === Feed Forward Network ===
        # FFN(x) = GELU(xW1 + b1)W2 + b2
        ff_output = np.dot(x, self.W_ff1) + self.b_ff1
        ff_output = self.gelu(ff_output)
        ff_output = np.dot(ff_output, self.W_ff2) + self.b_ff2

        # Add & Norm (Residual Connection)
        x = self.layer_norm(x + ff_output)

        return x, attn_weights

class SimpleGPT:
    """
    Simplified GPT Model
    Decoder-only transformer for text generation
    """
    def __init__(self, vocab_size, d_model=64, n_heads=4, n_layers=2, max_seq_len=128):
        """
        Parameters:
        vocab_size: Size of vocabulary
        d_model: Model dimension
        n_heads: Number of attention heads
        n_layers: Number of transformer layers
        max_seq_len: Maximum sequence length
        """
        self.vocab_size = vocab_size
        self.d_model = d_model
        self.n_heads = n_heads
        self.n_layers = n_layers
        self.max_seq_len = max_seq_len

        # Token embeddings
        self.token_embeddings = np.random.randn(vocab_size, d_model) * 0.01

        # Positional embeddings (learned)
        self.positional_embeddings = np.random.randn(max_seq_len, d_model) * 0.01

        # Transformer blocks
        self.blocks = [GPTBlock(d_model, n_heads, d_model * 4) for _ in range(n_layers)]

        # Output projection (to vocabulary)
        self.W_out = np.random.randn(d_model, vocab_size) * 0.01

    def forward(self, token_ids):
        """
        Forward pass

        token_ids: List of token indices [0, 1, 2, ...]
        Returns: Logits for next token prediction
        """
        seq_len = len(token_ids)

        # Embedding lookup
        x = self.token_embeddings[token_ids]  # (seq_len, d_model)

        # Add positional embeddings
        positions = np.arange(seq_len)
        x = x + self.positional_embeddings[positions]

        # Pass through transformer blocks
        attention_weights_per_layer = []
        for block in self.blocks:
            x, attn_weights = block.forward(x)
            attention_weights_per_layer.append(attn_weights)

        # Project to vocabulary
        logits = np.dot(x, self.W_out)  # (seq_len, vocab_size)

        return logits, attention_weights_per_layer

    def generate(self, start_tokens, max_new_tokens=10, temperature=1.0):
        """
        Autoregressive text generation

        start_tokens: Initial token sequence
        max_new_tokens: Number of tokens to generate
        temperature: Controls randomness (lower = more deterministic)
        """
        generated = list(start_tokens)

        for _ in range(max_new_tokens):
            # Get predictions for current sequence
            logits, _ = self.forward(generated)

            # Get logits for last position (next token prediction)
            next_token_logits = logits[-1] / temperature

            # Softmax to get probabilities
            probs = np.exp(next_token_logits - np.max(next_token_logits))
            probs = probs / np.sum(probs)

            # Sample next token
            next_token = np.random.choice(len(probs), p=probs)

            generated.append(next_token)

        return generated

def demonstrate_gpt():
    """Demonstrate GPT architecture"""
    print("=== GPT Architecture Demo ===\n")

    # Simple vocabulary
    vocab = ['<PAD>', '<START>', '<END>', 'the', 'cat', 'sat', 'on', 'mat', 'dog', 'run']
    vocab_size = len(vocab)
    word_to_idx = {word: idx for idx, word in enumerate(vocab)}
    idx_to_word = {idx: word for word, idx in word_to_idx.items()}

    print(f"Vocabulary ({vocab_size} tokens): {vocab}")

    # Create GPT model
    gpt = SimpleGPT(vocab_size=vocab_size, d_model=32, n_heads=4, n_layers=2)

    # Example input
    input_text = "the cat sat"
    input_tokens = [word_to_idx[word] for word in input_text.split()]

    print(f"\n1️⃣ Input: '{input_text}'")
    print(f"   Token IDs: {input_tokens}")

    # Forward pass
    logits, attention_weights = gpt.forward(input_tokens)

    print(f"\n2️⃣ Model Output:")
    print(f"   Logits shape: {logits.shape} (seq_len={len(input_tokens)}, vocab_size={vocab_size})")
    print(f"   Predicting next token after each position...")

    # Predict next token for last position
    last_logits = logits[-1]
    probs = np.exp(last_logits - np.max(last_logits))
    probs = probs / np.sum(probs)

    print(f"\n   Top-3 predictions after '{input_text}':")
    top_3_indices = np.argsort(probs)[-3:][::-1]
    for idx in top_3_indices:
        print(f"      {vocab[idx]}: {probs[idx]:.3f}")

    # Visualize masked attention
    print("\n3️⃣ Masked Self-Attention Pattern:")
    attn_layer0 = attention_weights[0]

    plt.figure(figsize=(15, 5))

    # Plot 1: Attention heatmap
    plt.subplot(1, 3, 1)
    plt.imshow(attn_layer0, cmap='YlOrRd', aspect='auto')
    plt.colorbar(label='Attention Weight')

    tokens = input_text.split()
    plt.xticks(range(len(tokens)), tokens)
    plt.yticks(range(len(tokens)), tokens)
    plt.xlabel('Keys (Attend to)')
    plt.ylabel('Queries (Attending from)')
    plt.title('Masked Attention Heatmap')

    # Add values
    for i in range(len(tokens)):
        for j in range(len(tokens)):
            if j <= i:  # Only show attended positions (causal mask)
                plt.text(j, i, f'{attn_layer0[i, j]:.2f}',
                        ha='center', va='center',
                        color='white' if attn_layer0[i, j] > 0.5 else 'black',
                        fontsize=10)

    # Plot 2: GPT Architecture Diagram
    plt.subplot(1, 3, 2)
    plt.text(0.5, 0.98, 'GPT Architecture', ha='center', va='top',
             fontsize=14, weight='bold', transform=plt.gca().transAxes)

    architecture = """
    Input: "the cat sat"
       ↓
    Token Embeddings
    [0.1, 0.2, ...] for "the"
    [0.3, 0.1, ...] for "cat"
    [0.2, 0.4, ...] for "sat"
       ↓
    + Positional Embeddings
    [pos_0, pos_1, pos_2]
       ↓
    ┌─────────────────────┐
    │  GPT Block 1        │
    │  ├─ Masked Attn     │  ◄─┐
    │  ├─ Add & Norm      │────┘
    │  ├─ Feed Forward    │  ◄─┐
    │  └─ Add & Norm      │────┘
    └─────────────────────┘
       ↓
    ┌─────────────────────┐
    │  GPT Block 2        │
    │  (same structure)   │
    └─────────────────────┘
       ↓
    ... (N layers)
       ↓
    Linear → Vocabulary
    [logits for each token]
       ↓
    Softmax → Probabilities
       ↓
    Sample Next Token

    Key Feature:
    🔹 Causal Masking
       Position i can only
       attend to positions
       j where j ≤ i

    Autoregressive:
    Generate one token
    at a time, left→right
    """

    plt.text(0.05, 0.92, architecture, ha='left', va='top',
             fontsize=8, family='monospace',
             transform=plt.gca().transAxes)
    plt.axis('off')

    # Plot 3: Causal mask visualization
    plt.subplot(1, 3, 3)
    mask = np.tril(np.ones((5, 5)))

    plt.imshow(mask, cmap='Blues', aspect='auto')
    plt.colorbar(label='Attention Allowed')

    positions = ['Pos 0', 'Pos 1', 'Pos 2', 'Pos 3', 'Pos 4']
    plt.xticks(range(5), positions, rotation=45)
    plt.yticks(range(5), positions)
    plt.xlabel('Key Position')
    plt.ylabel('Query Position')
    plt.title('Causal Attention Mask')

    plt.tight_layout()
    plt.savefig('gpt_architecture.png')
    print("   📊 Visualization saved to 'gpt_architecture.png'")

    # === Generate Text ===
    print("\n4️⃣ Text Generation (Autoregressive):")
    start = [word_to_idx['the'], word_to_idx['cat']]
    generated_ids = gpt.generate(start, max_new_tokens=3, temperature=0.8)

    generated_text = ' '.join([idx_to_word[idx] for idx in generated_ids])
    print(f"   Generated: {generated_text}")

    # === Explain Concepts ===
    print("\n📚 GPT Key Concepts:")
    print("   • Decoder-Only: Uses only transformer decoder")
    print("   • Causal Masking: Can't see future tokens")
    print("   • Autoregressive: Generates one token at a time")
    print("   • Pre-training: Trained on massive text corpus")
    print("   • Next Token Prediction: Core training objective")
    print("   • GELU Activation: Instead of ReLU")

    print("\n🎯 GPT Training:")
    print("   1. Pre-training: Learn language patterns from text")
    print("   2. Fine-tuning: Adapt to specific tasks")
    print("   3. Prompt Engineering: Guide with clever inputs")
    print("   4. Few-shot Learning: Learn from examples")

    print("\n📊 GPT Model Sizes:")
    print("   • GPT-1:   117M parameters, 12 layers")
    print("   • GPT-2:   1.5B parameters, 48 layers")
    print("   • GPT-3:   175B parameters, 96 layers")
    print("   • GPT-4:   [Architecture undisclosed]")

    print("\n💡 Applications:")
    print("   • Text Generation & Completion")
    print("   • Question Answering")
    print("   • Summarization")
    print("   • Translation")
    print("   • Code Generation (Codex, GitHub Copilot)")
    print("   • Chatbots (ChatGPT)")

    print("\n⚙️  Key Innovations:")
    print("   ✓ Massive scale (billions of parameters)")
    print("   ✓ Unsupervised pre-training")
    print("   ✓ Transfer learning via prompting")
    print("   ✓ In-context learning (few-shot)")
    print("   ✓ Emergent abilities at scale")

if __name__ == "__main__":
    demonstrate_gpt()
    print("\n✅ GPT Architecture Demo Complete!")
