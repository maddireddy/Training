"""
Neural Network from Scratch
Demonstrates: Multi-layer perceptron, backpropagation
"""

import numpy as np
import matplotlib.pyplot as plt

class NeuralNetwork:
    """
    Simple feedforward neural network
    Architecture: Input -> Hidden Layer -> Output Layer
    """
    def __init__(self, input_size, hidden_size, output_size):
        # Initialize weights with Xavier initialization
        self.W1 = np.random.randn(input_size, hidden_size) * np.sqrt(2.0/input_size)
        self.b1 = np.zeros((1, hidden_size))

        self.W2 = np.random.randn(hidden_size, output_size) * np.sqrt(2.0/hidden_size)
        self.b2 = np.zeros((1, output_size))

        self.cost_history = []
        self.accuracy_history = []

    def relu(self, Z):
        """ReLU activation function"""
        return np.maximum(0, Z)

    def relu_derivative(self, Z):
        """Derivative of ReLU"""
        return (Z > 0).astype(float)

    def softmax(self, Z):
        """Softmax activation for output layer"""
        exp_Z = np.exp(Z - np.max(Z, axis=1, keepdims=True))
        return exp_Z / np.sum(exp_Z, axis=1, keepdims=True)

    def forward(self, X):
        """Forward propagation"""
        # Hidden layer
        self.Z1 = np.dot(X, self.W1) + self.b1
        self.A1 = self.relu(self.Z1)

        # Output layer
        self.Z2 = np.dot(self.A1, self.W2) + self.b2
        self.A2 = self.softmax(self.Z2)

        return self.A2

    def backward(self, X, y, learning_rate):
        """Backpropagation"""
        m = X.shape[0]

        # Output layer gradients
        dZ2 = self.A2 - y
        dW2 = (1/m) * np.dot(self.A1.T, dZ2)
        db2 = (1/m) * np.sum(dZ2, axis=0, keepdims=True)

        # Hidden layer gradients
        dA1 = np.dot(dZ2, self.W2.T)
        dZ1 = dA1 * self.relu_derivative(self.Z1)
        dW1 = (1/m) * np.dot(X.T, dZ1)
        db1 = (1/m) * np.sum(dZ1, axis=0, keepdims=True)

        # Update parameters
        self.W2 -= learning_rate * dW2
        self.b2 -= learning_rate * db2
        self.W1 -= learning_rate * dW1
        self.b1 -= learning_rate * db1

    def compute_cost(self, y_pred, y_true):
        """Compute cross-entropy cost"""
        m = y_true.shape[0]
        cost = -np.sum(y_true * np.log(y_pred + 1e-8)) / m
        return cost

    def fit(self, X, y, epochs=1000, learning_rate=0.01, batch_size=32):
        """
        Train the neural network

        Parameters:
        X: Training data
        y: One-hot encoded labels
        epochs: Number of training iterations
        learning_rate: Learning rate
        batch_size: Mini-batch size
        """
        n_samples = X.shape[0]

        for epoch in range(epochs):
            # Mini-batch training
            indices = np.random.permutation(n_samples)
            X_shuffled = X[indices]
            y_shuffled = y[indices]

            for i in range(0, n_samples, batch_size):
                X_batch = X_shuffled[i:i+batch_size]
                y_batch = y_shuffled[i:i+batch_size]

                # Forward and backward pass
                y_pred = self.forward(X_batch)
                self.backward(X_batch, y_batch, learning_rate)

            # Calculate cost and accuracy on full dataset
            y_pred_full = self.forward(X)
            cost = self.compute_cost(y_pred_full, y)
            predictions = np.argmax(y_pred_full, axis=1)
            true_labels = np.argmax(y, axis=1)
            accuracy = np.mean(predictions == true_labels)

            self.cost_history.append(cost)
            self.accuracy_history.append(accuracy)

            if epoch % 100 == 0:
                print(f"Epoch {epoch}: Cost = {cost:.4f}, Accuracy = {accuracy*100:.2f}%")

    def predict(self, X):
        """Make predictions"""
        y_pred = self.forward(X)
        return np.argmax(y_pred, axis=1)

    def plot_training_history(self):
        """Visualize training progress"""
        plt.figure(figsize=(12, 5))

        # Plot cost
        plt.subplot(1, 2, 1)
        plt.plot(self.cost_history, color='red')
        plt.xlabel('Epoch')
        plt.ylabel('Cost')
        plt.title('Training Cost')
        plt.grid(True, alpha=0.3)

        # Plot accuracy
        plt.subplot(1, 2, 2)
        plt.plot(self.accuracy_history, color='blue')
        plt.xlabel('Epoch')
        plt.ylabel('Accuracy')
        plt.title('Training Accuracy')
        plt.grid(True, alpha=0.3)

        plt.tight_layout()
        plt.savefig('neural_network_training.png')
        print("📊 Training history saved to 'neural_network_training.png'")

# Example: XOR Problem
if __name__ == "__main__":
    print("=== Neural Network Example: XOR Problem ===\n")

    # XOR dataset
    X = np.array([[0, 0], [0, 1], [1, 0], [1, 1]])
    y_labels = np.array([0, 1, 1, 0])

    # One-hot encode labels
    y = np.eye(2)[y_labels]

    # Create and train network
    nn = NeuralNetwork(input_size=2, hidden_size=4, output_size=2)
    print("Training Neural Network on XOR problem...")
    nn.fit(X, y, epochs=5000, learning_rate=0.1, batch_size=4)

    # Test predictions
    predictions = nn.predict(X)

    print("\n📊 XOR Predictions:")
    print("Input  | True | Predicted")
    print("-------+------+----------")
    for i in range(len(X)):
        print(f"{X[i]} |  {y_labels[i]}   |     {predictions[i]}")

    # Visualize
    nn.plot_training_history()

    print("\n✅ Neural Network Training Complete!")
    print(f"Final Accuracy: {nn.accuracy_history[-1]*100:.2f}%")
