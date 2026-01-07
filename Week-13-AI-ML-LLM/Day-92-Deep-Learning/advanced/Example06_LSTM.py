"""
Long Short-Term Memory (LSTM)
Demonstrates: Memory cells, gates, long-term dependencies
"""

import numpy as np
import matplotlib.pyplot as plt

class LSTMCell:
    """
    LSTM Cell Implementation
    Solves vanishing gradient problem with gating mechanisms
    """
    def __init__(self, input_size, hidden_size):
        self.input_size = input_size
        self.hidden_size = hidden_size

        # Initialize weights for gates
        # Forget gate
        self.W_f = np.random.randn(input_size + hidden_size, hidden_size) * 0.01
        self.b_f = np.zeros((1, hidden_size))

        # Input gate
        self.W_i = np.random.randn(input_size + hidden_size, hidden_size) * 0.01
        self.b_i = np.zeros((1, hidden_size))

        # Cell gate (candidate values)
        self.W_c = np.random.randn(input_size + hidden_size, hidden_size) * 0.01
        self.b_c = np.zeros((1, hidden_size))

        # Output gate
        self.W_o = np.random.randn(input_size + hidden_size, hidden_size) * 0.01
        self.b_o = np.zeros((1, hidden_size))

    def sigmoid(self, x):
        """Sigmoid activation: σ(x) = 1 / (1 + e^(-x))"""
        return 1 / (1 + np.exp(-np.clip(x, -500, 500)))

    def tanh(self, x):
        """Tanh activation"""
        return np.tanh(np.clip(x, -500, 500))

    def forward(self, x_t, h_prev, c_prev):
        """
        LSTM Forward Pass

        Gates control information flow:
        1. Forget Gate: What to forget from cell state
        2. Input Gate: What new information to add
        3. Cell State Update: Combine forget & input
        4. Output Gate: What to output from cell state

        Parameters:
        x_t: Input at time t
        h_prev: Previous hidden state
        c_prev: Previous cell state

        Returns:
        h_t: New hidden state
        c_t: New cell state
        cache: Values for backward pass
        """
        # Concatenate input and previous hidden state
        concat = np.concatenate([x_t, h_prev], axis=1)

        # === FORGET GATE ===
        # Decides what information to throw away from cell state
        # f_t = σ(W_f · [h_{t-1}, x_t] + b_f)
        f_t = self.sigmoid(np.dot(concat, self.W_f) + self.b_f)

        # === INPUT GATE ===
        # Decides what new information to store in cell state
        # i_t = σ(W_i · [h_{t-1}, x_t] + b_i)
        i_t = self.sigmoid(np.dot(concat, self.W_i) + self.b_i)

        # === CELL GATE (CANDIDATE) ===
        # Creates candidate values to add to cell state
        # c̃_t = tanh(W_c · [h_{t-1}, x_t] + b_c)
        c_tilde = self.tanh(np.dot(concat, self.W_c) + self.b_c)

        # === CELL STATE UPDATE ===
        # Combine forget and input gates
        # c_t = f_t ⊙ c_{t-1} + i_t ⊙ c̃_t
        c_t = f_t * c_prev + i_t * c_tilde

        # === OUTPUT GATE ===
        # Decides what to output based on cell state
        # o_t = σ(W_o · [h_{t-1}, x_t] + b_o)
        o_t = self.sigmoid(np.dot(concat, self.W_o) + self.b_o)

        # === HIDDEN STATE ===
        # h_t = o_t ⊙ tanh(c_t)
        h_t = o_t * self.tanh(c_t)

        # Cache for backward pass
        cache = {
            'x_t': x_t, 'h_prev': h_prev, 'c_prev': c_prev,
            'concat': concat, 'f_t': f_t, 'i_t': i_t,
            'c_tilde': c_tilde, 'c_t': c_t, 'o_t': o_t,
            'h_t': h_t
        }

        return h_t, c_t, cache

class LSTM:
    """
    LSTM Network for Sequence Processing
    """
    def __init__(self, input_size, hidden_size, output_size):
        self.input_size = input_size
        self.hidden_size = hidden_size
        self.output_size = output_size

        # LSTM cell
        self.cell = LSTMCell(input_size, hidden_size)

        # Output layer
        self.W_y = np.random.randn(hidden_size, output_size) * 0.01
        self.b_y = np.zeros((1, output_size))

        self.loss_history = []

    def forward(self, inputs):
        """
        Forward pass through sequence

        inputs: List of input vectors
        """
        T = len(inputs)
        h_t = np.zeros((1, self.hidden_size))
        c_t = np.zeros((1, self.hidden_size))

        hidden_states = []
        cell_states = []
        outputs = []
        caches = []

        for t in range(T):
            x_t = inputs[t].reshape(1, -1)

            # LSTM forward
            h_t, c_t, cache = self.cell.forward(x_t, h_t, c_t)

            # Output
            y_t = np.dot(h_t, self.W_y) + self.b_y

            hidden_states.append(h_t)
            cell_states.append(c_t)
            outputs.append(y_t)
            caches.append(cache)

        return outputs, hidden_states, cell_states, caches

    def train_step(self, inputs, targets, learning_rate):
        """Single training step"""
        outputs, hidden_states, cell_states, caches = self.forward(inputs)

        # Compute loss
        loss = sum(np.sum((outputs[t] - targets[t])**2)
                  for t in range(len(inputs)))

        # Simple gradient descent on output layer
        dW_y = np.zeros_like(self.W_y)
        db_y = np.zeros_like(self.b_y)

        for t in range(len(inputs)):
            dy = 2 * (outputs[t] - targets[t])
            dW_y += np.dot(hidden_states[t].T, dy)
            db_y += dy

        # Update output weights
        self.W_y -= learning_rate * dW_y
        self.b_y -= learning_rate * db_y

        return loss

    def train(self, X_sequences, y_sequences, epochs=100, learning_rate=0.001):
        """Train LSTM"""
        for epoch in range(epochs):
            total_loss = 0

            for X_seq, y_seq in zip(X_sequences, y_sequences):
                loss = self.train_step(X_seq, y_seq, learning_rate)
                total_loss += loss

            avg_loss = total_loss / len(X_sequences)
            self.loss_history.append(avg_loss)

            if epoch % 10 == 0:
                print(f"Epoch {epoch}: Loss = {avg_loss:.4f}")

    def predict(self, inputs):
        """Predict on sequence"""
        outputs, _, _, _ = self.forward(inputs)
        return outputs

def demonstrate_lstm():
    """Demonstrate LSTM on sequence task"""
    print("=== LSTM Demo: Long-Term Dependencies ===\n")

    # Task: Remember first number and add to current
    # Example: [5, 3, 2, 4] → [8, 7, 9] (5+3, 5+2, 5+4)

    def generate_sequence(length=5):
        """Generate sequence where output depends on first element"""
        first = np.random.randint(1, 10)
        seq = [first] + [np.random.randint(1, 10) for _ in range(length-1)]
        inputs = [np.array([x]) for x in seq]
        # Target: add first element to each subsequent element
        targets = [np.array([first + seq[i+1]]) for i in range(length-1)]
        return inputs[:-1], targets

    # Generate training data
    X_sequences = []
    y_sequences = []

    for _ in range(100):
        X_seq, y_seq = generate_sequence(length=6)
        X_sequences.append(X_seq)
        y_sequences.append(y_seq)

    print(f"Training on {len(X_sequences)} sequences")
    print(f"Example:")
    print(f"  Input:  {[x[0] for x in X_sequences[0]]}")
    print(f"  Target: {[y[0] for y in y_sequences[0]]}")
    print("  (Each output = first input + current input)")

    # Create and train LSTM
    lstm = LSTM(input_size=1, hidden_size=20, output_size=1)
    print("\nTraining LSTM...")
    lstm.train(X_sequences, y_sequences, epochs=100, learning_rate=0.001)

    # Test
    print("\n📊 Test Predictions:")
    for _ in range(3):
        X_test, y_test = generate_sequence(length=6)
        predictions = lstm.predict(X_test)

        input_vals = [x[0] for x in X_test]
        target_vals = [y[0] for y in y_test]
        pred_vals = [p[0, 0] for p in predictions]

        print(f"  Input:     {input_vals}")
        print(f"  Expected:  {target_vals}")
        print(f"  Predicted: {[f'{p:.1f}' for p in pred_vals]}\n")

    # Visualize
    plt.figure(figsize=(15, 5))

    # Plot 1: Training loss
    plt.subplot(1, 3, 1)
    plt.plot(lstm.loss_history, color='green', linewidth=2)
    plt.xlabel('Epoch')
    plt.ylabel('Loss (MSE)')
    plt.title('LSTM Training Loss')
    plt.grid(True, alpha=0.3)

    # Plot 2: LSTM Cell Architecture
    plt.subplot(1, 3, 2)
    plt.text(0.5, 0.98, 'LSTM Cell Architecture',
             ha='center', va='top', fontsize=12, weight='bold',
             transform=plt.gca().transAxes)

    architecture = """
    ┌─────────────────────────────────┐
    │        LSTM Cell at time t      │
    │                                 │
    │  Input: x_t, h_{t-1}, c_{t-1}  │
    │                                 │
    │  ┌─────────┐                    │
    │  │ FORGET  │  f_t = σ(...)      │
    │  │  GATE   │  What to forget?   │
    │  └─────────┘         ↓          │
    │           ↓          ↓          │
    │  ┌─────────┐   ┌─────────┐     │
    │  │ INPUT   │   │  CELL   │     │
    │  │  GATE   │   │  GATE   │     │
    │  │ i_t=σ() │   │ c̃=tanh()│     │
    │  └─────────┘   └─────────┘     │
    │      ↓              ↓           │
    │      └──────┬───────┘           │
    │             ↓                   │
    │      c_t = f⊙c_{t-1} + i⊙c̃    │
    │             ↓                   │
    │       ┌─────────┐               │
    │       │ OUTPUT  │  o_t = σ(...) │
    │       │  GATE   │               │
    │       └─────────┘               │
    │             ↓                   │
    │       h_t = o ⊙ tanh(c_t)      │
    │                                 │
    │  Output: h_t, c_t               │
    └─────────────────────────────────┘
    """

    plt.text(0.05, 0.92, architecture, ha='left', va='top',
             fontsize=8, family='monospace',
             transform=plt.gca().transAxes)
    plt.axis('off')

    # Plot 3: Gates visualization concept
    plt.subplot(1, 3, 3)
    plt.text(0.5, 0.98, 'LSTM Gates',
             ha='center', va='top', fontsize=12, weight='bold',
             transform=plt.gca().transAxes)

    gates_text = """
    🚪 Forget Gate (f_t):
       "What should I forget from
        the previous cell state?"
       Range: 0 (forget all) to 1 (keep all)

    📥 Input Gate (i_t):
       "What new information should
        I store in cell state?"
       Range: 0 (ignore) to 1 (accept all)

    📝 Cell Gate (c̃_t):
       "Candidate values to add
        to the cell state"
       Range: -1 to 1 (tanh)

    📤 Output Gate (o_t):
       "What should I output based
        on the cell state?"
       Range: 0 (output nothing) to 1

    Key Advantage:
    • Gradient highway through cell state
    • Can learn long-term dependencies
    • Gates control information flow
    """

    plt.text(0.05, 0.90, gates_text, ha='left', va='top',
             fontsize=9, transform=plt.gca().transAxes)
    plt.axis('off')

    plt.tight_layout()
    plt.savefig('lstm_results.png')
    print("📊 Results saved to 'lstm_results.png'")

    # Explain concepts
    print("\n📚 LSTM Key Concepts:")
    print("   • Cell State (c_t): Long-term memory highway")
    print("   • Hidden State (h_t): Short-term working memory")
    print("   • Forget Gate: Controls what to discard")
    print("   • Input Gate: Controls what to add")
    print("   • Output Gate: Controls what to output")
    print("   • Gates use sigmoid (0-1 range)")

    print("\n🎯 Advantages over RNN:")
    print("   ✓ Solves vanishing gradient problem")
    print("   ✓ Learns long-term dependencies")
    print("   ✓ Better gradient flow through cell state")
    print("   ✓ More stable training")

    print("\n💡 Applications:")
    print("   • Machine Translation")
    print("   • Speech Recognition")
    print("   • Text Generation")
    print("   • Video Captioning")
    print("   • Music Generation")
    print("   • Time Series Forecasting")

    print("\n⚙️  Variants:")
    print("   • GRU: Simpler (2 gates instead of 3)")
    print("   • Peephole LSTM: Gates see cell state")
    print("   • Bidirectional LSTM: Process both directions")

if __name__ == "__main__":
    demonstrate_lstm()
    print("\n✅ LSTM Demo Complete!")
