"""
Recurrent Neural Network (RNN)
Demonstrates: Sequential data processing, hidden states, time dependencies
"""

import numpy as np
import matplotlib.pyplot as plt

class SimpleRNN:
    """
    Simple Recurrent Neural Network
    Processes sequences by maintaining hidden state
    """
    def __init__(self, input_size, hidden_size, output_size):
        """
        Parameters:
        input_size: Dimension of input features
        hidden_size: Dimension of hidden state
        output_size: Dimension of output
        """
        self.input_size = input_size
        self.hidden_size = hidden_size
        self.output_size = output_size

        # Initialize weights
        # W_xh: Input to hidden
        self.W_xh = np.random.randn(input_size, hidden_size) * 0.01
        # W_hh: Hidden to hidden (recurrent connection)
        self.W_hh = np.random.randn(hidden_size, hidden_size) * 0.01
        # W_hy: Hidden to output
        self.W_hy = np.random.randn(hidden_size, output_size) * 0.01

        # Biases
        self.b_h = np.zeros((1, hidden_size))
        self.b_y = np.zeros((1, output_size))

        self.loss_history = []

    def tanh(self, x):
        """Tanh activation"""
        return np.tanh(x)

    def tanh_derivative(self, x):
        """Derivative of tanh"""
        return 1 - np.tanh(x)**2

    def softmax(self, x):
        """Softmax activation"""
        exp_x = np.exp(x - np.max(x, axis=-1, keepdims=True))
        return exp_x / np.sum(exp_x, axis=-1, keepdims=True)

    def forward(self, inputs):
        """
        Forward pass through time

        inputs: List of input vectors [x_0, x_1, ..., x_T]
        Returns: outputs, hidden_states
        """
        T = len(inputs)  # Sequence length
        hidden_states = []
        outputs = []

        # Initial hidden state (zeros)
        h = np.zeros((1, self.hidden_size))

        for t in range(T):
            x_t = inputs[t].reshape(1, -1)

            # RNN cell computation:
            # h_t = tanh(x_t @ W_xh + h_{t-1} @ W_hh + b_h)
            h = self.tanh(
                np.dot(x_t, self.W_xh) +
                np.dot(h, self.W_hh) +
                self.b_h
            )

            # Output: y_t = h_t @ W_hy + b_y
            y = np.dot(h, self.W_hy) + self.b_y

            hidden_states.append(h)
            outputs.append(y)

        return outputs, hidden_states

    def backward(self, inputs, targets, outputs, hidden_states, learning_rate):
        """
        Backpropagation Through Time (BPTT)
        """
        T = len(inputs)

        # Initialize gradients
        dW_xh = np.zeros_like(self.W_xh)
        dW_hh = np.zeros_like(self.W_hh)
        dW_hy = np.zeros_like(self.W_hy)
        db_h = np.zeros_like(self.b_h)
        db_y = np.zeros_like(self.b_y)

        dh_next = np.zeros((1, self.hidden_size))

        # Backward pass through time
        for t in reversed(range(T)):
            x_t = inputs[t].reshape(1, -1)
            y_t = outputs[t]
            target_t = targets[t].reshape(1, -1)

            # Output layer gradient
            dy = y_t - target_t
            dW_hy += np.dot(hidden_states[t].T, dy)
            db_y += dy

            # Hidden layer gradient
            dh = np.dot(dy, self.W_hy.T) + dh_next

            # Tanh gradient
            if t > 0:
                h_raw = np.dot(x_t, self.W_xh) + np.dot(hidden_states[t-1], self.W_hh) + self.b_h
            else:
                h_raw = np.dot(x_t, self.W_xh) + self.b_h

            dh_raw = dh * self.tanh_derivative(h_raw)

            # Weight gradients
            dW_xh += np.dot(x_t.T, dh_raw)
            db_h += dh_raw

            if t > 0:
                dW_hh += np.dot(hidden_states[t-1].T, dh_raw)
                dh_next = np.dot(dh_raw, self.W_hh.T)
            else:
                dh_next = np.zeros_like(dh_next)

        # Clip gradients to prevent explosion
        for grad in [dW_xh, dW_hh, dW_hy, db_h, db_y]:
            np.clip(grad, -5, 5, out=grad)

        # Update weights
        self.W_xh -= learning_rate * dW_xh
        self.W_hh -= learning_rate * dW_hh
        self.W_hy -= learning_rate * dW_hy
        self.b_h -= learning_rate * db_h
        self.b_y -= learning_rate * db_y

    def train(self, X_sequences, y_sequences, epochs=100, learning_rate=0.01):
        """
        Train RNN on sequences

        X_sequences: List of input sequences
        y_sequences: List of target sequences
        """
        for epoch in range(epochs):
            total_loss = 0

            for X_seq, y_seq in zip(X_sequences, y_sequences):
                # Forward pass
                outputs, hidden_states = self.forward(X_seq)

                # Compute loss (MSE for simplicity)
                loss = sum(np.sum((outputs[t] - y_seq[t])**2)
                          for t in range(len(X_seq)))
                total_loss += loss

                # Backward pass
                self.backward(X_seq, y_seq, outputs, hidden_states, learning_rate)

            avg_loss = total_loss / len(X_sequences)
            self.loss_history.append(avg_loss)

            if epoch % 10 == 0:
                print(f"Epoch {epoch}: Loss = {avg_loss:.4f}")

    def predict(self, inputs):
        """Predict on new sequence"""
        outputs, _ = self.forward(inputs)
        return outputs

def demonstrate_rnn():
    """Demonstrate RNN on simple sequence prediction"""
    print("=== RNN Demo: Sequence Prediction ===\n")

    # Task: Predict next number in sequence
    # Example: [0, 1, 2] → [1, 2, 3]

    # Generate training sequences
    def generate_sequence(start, length):
        """Generate arithmetic sequence"""
        return [np.array([start + i]) for i in range(length)]

    # Training data
    X_sequences = []
    y_sequences = []

    for start in range(0, 20):
        X_seq = generate_sequence(start, 5)
        y_seq = generate_sequence(start + 1, 5)
        X_sequences.append(X_seq)
        y_sequences.append(y_seq)

    print(f"Training on {len(X_sequences)} sequences")
    print(f"Example input:  {[x[0] for x in X_sequences[0]]}")
    print(f"Example target: {[y[0] for y in y_sequences[0]]}")

    # Create and train RNN
    rnn = SimpleRNN(input_size=1, hidden_size=10, output_size=1)
    print("\nTraining RNN...")
    rnn.train(X_sequences, y_sequences, epochs=100, learning_rate=0.01)

    # Test predictions
    print("\n📊 Test Predictions:")
    test_sequences = [
        generate_sequence(25, 5),
        generate_sequence(50, 5),
        generate_sequence(100, 5)
    ]

    for test_seq in test_sequences:
        predictions = rnn.predict(test_seq)
        input_vals = [x[0] for x in test_seq]
        pred_vals = [p[0, 0] for p in predictions]

        print(f"  Input: {input_vals}")
        print(f"  Predicted: {[f'{p:.1f}' for p in pred_vals]}")

    # Visualize
    plt.figure(figsize=(12, 5))

    # Plot 1: Training loss
    plt.subplot(1, 2, 1)
    plt.plot(rnn.loss_history, color='blue', linewidth=2)
    plt.xlabel('Epoch')
    plt.ylabel('Loss (MSE)')
    plt.title('RNN Training Loss')
    plt.grid(True, alpha=0.3)

    # Plot 2: RNN architecture
    plt.subplot(1, 2, 2)
    plt.text(0.5, 0.95, 'RNN Architecture',
             ha='center', va='top', fontsize=14, weight='bold',
             transform=plt.gca().transAxes)

    architecture = """
    Time step:    t-1         t          t+1
                   ↓          ↓           ↓
    Input:        x₀         x₁          x₂
                   ↓          ↓           ↓
                 ┌───┐  →  ┌───┐  →   ┌───┐
    Hidden:      │ h₀│  →  │ h₁│  →   │ h₂│ →
                 └───┘     └───┘      └───┘
                   ↓          ↓           ↓
    Output:       y₀         y₁          y₂

    RNN Cell:
    h_t = tanh(W_xh·x_t + W_hh·h_{t-1} + b_h)
    y_t = W_hy·h_t + b_y

    Key Features:
    • Shared weights across time steps
    • Hidden state h carries information
    • Sequential processing (left to right)
    """

    plt.text(0.05, 0.85, architecture, ha='left', va='top',
             fontsize=9, family='monospace',
             transform=plt.gca().transAxes)
    plt.axis('off')

    plt.tight_layout()
    plt.savefig('rnn_results.png')
    print("\n📊 Results saved to 'rnn_results.png'")

    # Explain concepts
    print("\n📚 RNN Concepts:")
    print("   • Sequential Processing: Process one timestep at a time")
    print("   • Hidden State: Carries information from past")
    print("   • Recurrent Connection: h_t depends on h_{t-1}")
    print("   • Shared Weights: Same parameters for all timesteps")
    print("   • BPTT: Backpropagation Through Time")

    print("\n🎯 Applications:")
    print("   • Language Modeling (predict next word)")
    print("   • Time Series Prediction")
    print("   • Speech Recognition")
    print("   • Machine Translation")
    print("   • Video Analysis")

    print("\n⚠️  Challenges:")
    print("   ✗ Vanishing Gradients: Hard to learn long dependencies")
    print("   ✗ Exploding Gradients: Gradients grow exponentially")
    print("   ✗ Sequential Nature: Hard to parallelize")
    print("   → Solutions: LSTM, GRU, gradient clipping")

if __name__ == "__main__":
    demonstrate_rnn()
    print("\n✅ RNN Demo Complete!")
