"""
Machine Learning Basics - Linear Regression
Demonstrates: Simple linear regression from scratch
"""

import numpy as np
import matplotlib.pyplot as plt

class LinearRegression:
    """
    Simple Linear Regression Implementation
    Formula: y = mx + b
    """
    def __init__(self):
        self.m = 0  # slope
        self.b = 0  # intercept
        self.cost_history = []

    def fit(self, X, y, learning_rate=0.01, epochs=1000):
        """
        Train the model using gradient descent

        Parameters:
        X: Input features
        y: Target values
        learning_rate: Step size for gradient descent
        epochs: Number of training iterations
        """
        n = len(X)

        for epoch in range(epochs):
            # Predictions
            y_pred = self.m * X + self.b

            # Calculate cost (Mean Squared Error)
            cost = (1/(2*n)) * np.sum((y_pred - y)**2)
            self.cost_history.append(cost)

            # Calculate gradients
            dm = (1/n) * np.sum((y_pred - y) * X)
            db = (1/n) * np.sum(y_pred - y)

            # Update parameters
            self.m -= learning_rate * dm
            self.b -= learning_rate * db

            if epoch % 100 == 0:
                print(f"Epoch {epoch}: Cost = {cost:.4f}, m = {self.m:.4f}, b = {self.b:.4f}")

    def predict(self, X):
        """Make predictions"""
        return self.m * X + self.b

    def plot_results(self, X, y):
        """Visualize the regression line"""
        plt.figure(figsize=(12, 5))

        # Plot 1: Data and regression line
        plt.subplot(1, 2, 1)
        plt.scatter(X, y, color='blue', alpha=0.5, label='Data')
        plt.plot(X, self.predict(X), color='red', linewidth=2, label='Regression Line')
        plt.xlabel('X')
        plt.ylabel('y')
        plt.title(f'Linear Regression: y = {self.m:.2f}x + {self.b:.2f}')
        plt.legend()
        plt.grid(True, alpha=0.3)

        # Plot 2: Cost history
        plt.subplot(1, 2, 2)
        plt.plot(self.cost_history, color='green')
        plt.xlabel('Epoch')
        plt.ylabel('Cost (MSE)')
        plt.title('Training Progress')
        plt.grid(True, alpha=0.3)

        plt.tight_layout()
        plt.savefig('linear_regression_results.png')
        print("📊 Results saved to 'linear_regression_results.png'")

# Example Usage
if __name__ == "__main__":
    print("=== Linear Regression Example ===\n")

    # Generate sample data: y = 2x + 1 + noise
    np.random.seed(42)
    X = np.random.rand(100) * 10
    y = 2 * X + 1 + np.random.randn(100) * 2

    # Create and train model
    model = LinearRegression()
    print("Training model...")
    model.fit(X, y, learning_rate=0.01, epochs=1000)

    # Make predictions
    test_X = np.array([3, 5, 7])
    predictions = model.predict(test_X)

    print(f"\n📈 Predictions:")
    for x, pred in zip(test_X, predictions):
        print(f"  X = {x} → Predicted y = {pred:.2f}")

    # Visualize
    model.plot_results(X, y)

    print("\n✅ Linear Regression Complete!")
    print(f"Final Model: y = {model.m:.2f}x + {model.b:.2f}")
