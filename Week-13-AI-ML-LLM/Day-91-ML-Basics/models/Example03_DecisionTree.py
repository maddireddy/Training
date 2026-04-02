"""
Decision Tree Classifier
Demonstrates: Tree-based learning, entropy, information gain
"""

import numpy as np
import matplotlib.pyplot as plt
from collections import Counter

class Node:
    """Decision Tree Node"""
    def __init__(self, feature=None, threshold=None, left=None, right=None, value=None):
        self.feature = feature      # Feature index to split on
        self.threshold = threshold  # Threshold value for split
        self.left = left           # Left subtree
        self.right = right         # Right subtree
        self.value = value         # Leaf node class value

    def is_leaf(self):
        return self.value is not None

class DecisionTreeClassifier:
    """
    Decision Tree Implementation using ID3 algorithm
    Uses entropy and information gain for splitting
    """
    def __init__(self, max_depth=10, min_samples_split=2):
        self.max_depth = max_depth
        self.min_samples_split = min_samples_split
        self.root = None

    def entropy(self, y):
        """
        Calculate entropy: H(S) = -Σ(p_i * log2(p_i))
        Measures impurity/uncertainty in the dataset
        """
        counts = Counter(y)
        probabilities = [count / len(y) for count in counts.values()]
        entropy = -sum(p * np.log2(p) for p in probabilities if p > 0)
        return entropy

    def information_gain(self, parent, left_child, right_child):
        """
        Calculate information gain from a split
        IG = H(parent) - weighted_average(H(children))
        """
        weight_left = len(left_child) / len(parent)
        weight_right = len(right_child) / len(parent)

        gain = self.entropy(parent) - (
            weight_left * self.entropy(left_child) +
            weight_right * self.entropy(right_child)
        )
        return gain

    def split(self, X, y, feature, threshold):
        """Split dataset based on feature and threshold"""
        left_mask = X[:, feature] <= threshold
        right_mask = ~left_mask

        left_X, left_y = X[left_mask], y[left_mask]
        right_X, right_y = X[right_mask], y[right_mask]

        return left_X, left_y, right_X, right_y

    def best_split(self, X, y):
        """Find the best feature and threshold to split on"""
        best_gain = -1
        best_feature = None
        best_threshold = None

        n_features = X.shape[1]

        for feature in range(n_features):
            thresholds = np.unique(X[:, feature])

            for threshold in thresholds:
                left_X, left_y, right_X, right_y = self.split(X, y, feature, threshold)

                if len(left_y) == 0 or len(right_y) == 0:
                    continue

                gain = self.information_gain(y, left_y, right_y)

                if gain > best_gain:
                    best_gain = gain
                    best_feature = feature
                    best_threshold = threshold

        return best_feature, best_threshold, best_gain

    def build_tree(self, X, y, depth=0):
        """Recursively build the decision tree"""
        n_samples = len(y)
        n_classes = len(np.unique(y))

        # Stopping criteria
        if (depth >= self.max_depth or
            n_classes == 1 or
            n_samples < self.min_samples_split):
            # Create leaf node with most common class
            leaf_value = Counter(y).most_common(1)[0][0]
            return Node(value=leaf_value)

        # Find best split
        best_feature, best_threshold, best_gain = self.best_split(X, y)

        if best_feature is None:
            leaf_value = Counter(y).most_common(1)[0][0]
            return Node(value=leaf_value)

        # Split data
        left_X, left_y, right_X, right_y = self.split(
            X, y, best_feature, best_threshold
        )

        # Recursively build left and right subtrees
        left_subtree = self.build_tree(left_X, left_y, depth + 1)
        right_subtree = self.build_tree(right_X, right_y, depth + 1)

        return Node(best_feature, best_threshold, left_subtree, right_subtree)

    def fit(self, X, y):
        """Train the decision tree"""
        self.root = self.build_tree(X, y)

    def predict_sample(self, x, node):
        """Predict single sample by traversing tree"""
        if node.is_leaf():
            return node.value

        if x[node.feature] <= node.threshold:
            return self.predict_sample(x, node.left)
        else:
            return self.predict_sample(x, node.right)

    def predict(self, X):
        """Predict for multiple samples"""
        return np.array([self.predict_sample(x, self.root) for x in X])

    def visualize_tree(self, node=None, depth=0, prefix="Root: "):
        """Print tree structure"""
        if node is None:
            node = self.root

        if node.is_leaf():
            print(f"{' ' * depth * 4}{prefix}Class = {node.value}")
        else:
            print(f"{' ' * depth * 4}{prefix}Feature[{node.feature}] <= {node.threshold:.2f}")
            self.visualize_tree(node.left, depth + 1, "├─ True:  ")
            self.visualize_tree(node.right, depth + 1, "└─ False: ")

def demonstrate_decision_tree():
    """Demonstrate decision tree on synthetic data"""
    print("=== Decision Tree Classifier Demo ===\n")

    # Generate sample data
    np.random.seed(42)

    # Class 0: Bottom-left quadrant
    X0 = np.random.randn(50, 2) + np.array([2, 2])
    # Class 1: Top-right quadrant
    X1 = np.random.randn(50, 2) + np.array([6, 6])
    # Class 2: Top-left quadrant
    X2 = np.random.randn(50, 2) + np.array([2, 6])

    X = np.vstack([X0, X1, X2])
    y = np.array([0]*50 + [1]*50 + [2]*50)

    # Shuffle
    indices = np.random.permutation(len(X))
    X = X[indices]
    y = y[indices]

    # Train decision tree
    tree = DecisionTreeClassifier(max_depth=5, min_samples_split=2)
    print("Training Decision Tree...")
    tree.fit(X, y)

    # Make predictions
    predictions = tree.predict(X)
    accuracy = np.mean(predictions == y)

    print(f"\n✅ Training Accuracy: {accuracy*100:.2f}%")

    # Visualize tree structure
    print("\n🌳 Decision Tree Structure:")
    tree.visualize_tree()

    # Visualize decision boundary
    plt.figure(figsize=(12, 5))

    # Plot 1: Data points
    plt.subplot(1, 2, 1)
    colors = ['red', 'blue', 'green']
    for class_val in range(3):
        mask = y == class_val
        plt.scatter(X[mask, 0], X[mask, 1],
                   color=colors[class_val], alpha=0.6,
                   label=f'Class {class_val}', s=50)

    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.title('Training Data')
    plt.legend()
    plt.grid(True, alpha=0.3)

    # Plot 2: Decision boundary
    plt.subplot(1, 2, 2)

    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1

    xx, yy = np.meshgrid(np.linspace(x_min, x_max, 200),
                         np.linspace(y_min, y_max, 200))

    Z = tree.predict(np.c_[xx.ravel(), yy.ravel()])
    Z = Z.reshape(xx.shape)

    plt.contourf(xx, yy, Z, alpha=0.3, cmap='RdYlGn')

    for class_val in range(3):
        mask = y == class_val
        plt.scatter(X[mask, 0], X[mask, 1],
                   color=colors[class_val], alpha=0.8,
                   label=f'Class {class_val}', s=50, edgecolors='black')

    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.title('Decision Boundary')
    plt.legend()
    plt.grid(True, alpha=0.3)

    plt.tight_layout()
    plt.savefig('decision_tree_results.png')
    print("\n📊 Results saved to 'decision_tree_results.png'")

    # Explain concept
    print("\n📚 Decision Tree Concepts:")
    print("   • Entropy: Measures impurity (0 = pure, 1 = maximum impurity)")
    print("   • Information Gain: Reduction in entropy after split")
    print("   • Splitting: Choose feature & threshold that maximizes gain")
    print("   • Recursion: Build tree top-down until stopping criteria")
    print("   • Leaf Nodes: Contain class predictions")

    print("\n🎯 Advantages:")
    print("   ✓ Easy to understand and interpret")
    print("   ✓ Works with numerical and categorical data")
    print("   ✓ No need for feature scaling")
    print("   ✓ Non-linear relationships captured")

    print("\n⚠️  Disadvantages:")
    print("   ✗ Prone to overfitting")
    print("   ✗ Unstable (small changes = different tree)")
    print("   ✗ Biased toward dominant classes")

if __name__ == "__main__":
    demonstrate_decision_tree()
    print("\n✅ Decision Tree Demo Complete!")
