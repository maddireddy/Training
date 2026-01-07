"""
Logistic Regression for Binary Classification
Demonstrates: Sigmoid function, binary classification
"""

import numpy as np
import matplotlib.pyplot as plt

class LogisticRegression:
    """
    Binary Classification using Logistic Regression
    Uses sigmoid activation: σ(z) = 1 / (1 + e^(-z))
    """
    def __init__(self):
        self.weights = None
        self.bias = 0
        self.cost_history = []

    def sigmoid(self, z):
        """Sigmoid activation function"""
        return 1 / (1 + np.exp(-z))

    def fit(self, X, y, learning_rate=0.01, epochs=1000):
        """
        Train logistic regression model

        Parameters:
        X: Feature matrix (n_samples, n_features)
        y: Target labels (0 or 1)
        """
        n_samples, n_features = X.shape

        # Initialize weights
        self.weights = np.zeros(n_features)
        self.bias = 0

        for epoch in range(epochs):
            # Forward pass
            linear_model = np.dot(X, self.weights) + self.bias
            y_pred = self.sigmoid(linear_model)

            # Compute cost (Binary Cross-Entropy)
            cost = -np.mean(y * np.log(y_pred + 1e-15) +
                           (1 - y) * np.log(1 - y_pred + 1e-15))
            self.cost_history.append(cost)

            # Compute gradients
            dw = (1/n_samples) * np.dot(X.T, (y_pred - y))
            db = (1/n_samples) * np.sum(y_pred - y)

            # Update parameters
            self.weights -= learning_rate * dw
            self.bias -= learning_rate * db

            if epoch % 100 == 0:
                print(f"Epoch {epoch}: Cost = {cost:.4f}")

    def predict(self, X, threshold=0.5):
        """Predict class labels"""
        linear_model = np.dot(X, self.weights) + self.bias
        y_pred = self.sigmoid(linear_model)
        return (y_pred >= threshold).astype(int)

    def predict_proba(self, X):
        """Predict probability of positive class"""
        linear_model = np.dot(X, self.weights) + self.bias
        return self.sigmoid(linear_model)

    def plot_decision_boundary(self, X, y):
        """Visualize decision boundary (for 2D data)"""
        if X.shape[1] != 2:
            print("⚠️  Can only plot for 2D features")
            return

        plt.figure(figsize=(12, 5))

        # Plot 1: Decision boundary
        plt.subplot(1, 2, 1)

        # Plot data points
        plt.scatter(X[y == 0, 0], X[y == 0, 1], color='red',
                   alpha=0.5, label='Class 0', marker='o')
        plt.scatter(X[y == 1, 0], X[y == 1, 1], color='blue',
                   alpha=0.5, label='Class 1', marker='x')

        # Plot decision boundary
        x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
        y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1

        xx, yy = np.meshgrid(np.linspace(x_min, x_max, 100),
                            np.linspace(y_min, y_max, 100))

        Z = self.predict(np.c_[xx.ravel(), yy.ravel()])
        Z = Z.reshape(xx.shape)

        plt.contourf(xx, yy, Z, alpha=0.2, cmap='RdBu')
        plt.contour(xx, yy, Z, colors='black', linewidths=1, levels=[0.5])

        plt.xlabel('Feature 1')
        plt.ylabel('Feature 2')
        plt.title('Decision Boundary')
        plt.legend()
        plt.grid(True, alpha=0.3)

        # Plot 2: Cost history
        plt.subplot(1, 2, 2)
        plt.plot(self.cost_history, color='green')
        plt.xlabel('Epoch')
        plt.ylabel('Cost (Binary Cross-Entropy)')
        plt.title('Training Progress')
        plt.grid(True, alpha=0.3)

        plt.tight_layout()
        plt.savefig('logistic_regression_results.png')
        print("📊 Results saved to 'logistic_regression_results.png'")

# Example Usage
if __name__ == "__main__":
    print("=== Logistic Regression Example ===\n")

    # Generate sample data
    np.random.seed(42)

    # Class 0
    X0 = np.random.randn(50, 2) + np.array([2, 2])
    # Class 1
    X1 = np.random.randn(50, 2) + np.array([5, 5])

    X = np.vstack([X0, X1])
    y = np.hstack([np.zeros(50), np.ones(50)])

    # Shuffle data
    indices = np.random.permutation(len(X))
    X = X[indices]
    y = y[indices]

    # Train model
    model = LogisticRegression()
    print("Training model...")
    model.fit(X, y, learning_rate=0.1, epochs=1000)

    # Make predictions
    predictions = model.predict(X)
    probabilities = model.predict_proba(X)

    # Calculate accuracy
    accuracy = np.mean(predictions == y)
    print(f"\n✅ Training Accuracy: {accuracy*100:.2f}%")

    # Show some predictions
    print("\n📊 Sample Predictions:")
    for i in range(5):
        print(f"  Features: {X[i]}, True: {int(y[i])}, "
              f"Predicted: {predictions[i]}, Probability: {probabilities[i]:.4f}")

    # Visualize
    model.plot_decision_boundary(X, y)

    print("\n✅ Logistic Regression Complete!")
