"""
Random Forest Classifier
Demonstrates: Ensemble learning, bagging, voting
"""

import numpy as np
from collections import Counter
import matplotlib.pyplot as plt

class SimpleDecisionTree:
    """Simplified Decision Tree for Random Forest"""
    def __init__(self, max_depth=10, min_samples_split=2, n_features=None):
        self.max_depth = max_depth
        self.min_samples_split = min_samples_split
        self.n_features = n_features  # Number of features to consider
        self.root = None

    def entropy(self, y):
        """Calculate entropy"""
        counts = Counter(y)
        probabilities = [count / len(y) for count in counts.values()]
        return -sum(p * np.log2(p) for p in probabilities if p > 0)

    def information_gain(self, parent, left, right):
        """Calculate information gain"""
        weight_left = len(left) / len(parent)
        weight_right = len(right) / len(parent)
        return self.entropy(parent) - (
            weight_left * self.entropy(left) +
            weight_right * self.entropy(right)
        )

    def split(self, X, y, feature, threshold):
        """Split dataset"""
        left_mask = X[:, feature] <= threshold
        return (X[left_mask], y[left_mask],
                X[~left_mask], y[~left_mask])

    def best_split(self, X, y, features):
        """Find best split among random features"""
        best_gain = -1
        best_feature = None
        best_threshold = None

        for feature in features:
            thresholds = np.unique(X[:, feature])
            for threshold in thresholds:
                left_X, left_y, right_X, right_y = self.split(
                    X, y, feature, threshold
                )
                if len(left_y) == 0 or len(right_y) == 0:
                    continue

                gain = self.information_gain(y, left_y, right_y)
                if gain > best_gain:
                    best_gain = gain
                    best_feature = feature
                    best_threshold = threshold

        return best_feature, best_threshold

    def build_tree(self, X, y, depth=0):
        """Build tree with random feature subset"""
        n_samples, n_features = X.shape
        n_classes = len(np.unique(y))

        # Stopping criteria
        if (depth >= self.max_depth or
            n_classes == 1 or
            n_samples < self.min_samples_split):
            return {'value': Counter(y).most_common(1)[0][0]}

        # Random feature selection
        if self.n_features is None:
            self.n_features = int(np.sqrt(n_features))

        feature_indices = np.random.choice(
            n_features, self.n_features, replace=False
        )

        best_feature, best_threshold = self.best_split(X, y, feature_indices)

        if best_feature is None:
            return {'value': Counter(y).most_common(1)[0][0]}

        left_X, left_y, right_X, right_y = self.split(
            X, y, best_feature, best_threshold
        )

        return {
            'feature': best_feature,
            'threshold': best_threshold,
            'left': self.build_tree(left_X, left_y, depth + 1),
            'right': self.build_tree(right_X, right_y, depth + 1)
        }

    def fit(self, X, y):
        """Train decision tree"""
        self.root = self.build_tree(X, y)

    def predict_sample(self, x, node):
        """Predict single sample"""
        if 'value' in node:
            return node['value']

        if x[node['feature']] <= node['threshold']:
            return self.predict_sample(x, node['left'])
        else:
            return self.predict_sample(x, node['right'])

    def predict(self, X):
        """Predict multiple samples"""
        return np.array([self.predict_sample(x, self.root) for x in X])

class RandomForestClassifier:
    """
    Random Forest: Ensemble of Decision Trees
    Uses Bootstrap Aggregating (Bagging) and Random Feature Selection
    """
    def __init__(self, n_trees=10, max_depth=10,
                 min_samples_split=2, n_features=None):
        """
        Parameters:
        n_trees: Number of trees in forest
        max_depth: Maximum depth of each tree
        min_samples_split: Minimum samples required to split
        n_features: Number of features to consider for each split
        """
        self.n_trees = n_trees
        self.max_depth = max_depth
        self.min_samples_split = min_samples_split
        self.n_features = n_features
        self.trees = []

    def bootstrap_sample(self, X, y):
        """
        Create bootstrap sample (sampling with replacement)
        This introduces diversity among trees
        """
        n_samples = X.shape[0]
        indices = np.random.choice(n_samples, n_samples, replace=True)
        return X[indices], y[indices]

    def fit(self, X, y):
        """
        Train Random Forest
        1. Create multiple bootstrap samples
        2. Train one tree on each sample
        3. Each tree uses random feature subset
        """
        self.trees = []

        for i in range(self.n_trees):
            # Create bootstrap sample
            X_sample, y_sample = self.bootstrap_sample(X, y)

            # Train tree
            tree = SimpleDecisionTree(
                max_depth=self.max_depth,
                min_samples_split=self.min_samples_split,
                n_features=self.n_features
            )
            tree.fit(X_sample, y_sample)
            self.trees.append(tree)

            if (i + 1) % 10 == 0:
                print(f"  Trained {i + 1}/{self.n_trees} trees")

    def predict(self, X):
        """
        Predict using majority voting
        Each tree votes, final prediction is majority class
        """
        # Get predictions from all trees
        tree_predictions = np.array([tree.predict(X) for tree in self.trees])

        # Majority voting
        predictions = []
        for i in range(X.shape[0]):
            votes = tree_predictions[:, i]
            predictions.append(Counter(votes).most_common(1)[0][0])

        return np.array(predictions)

    def predict_proba(self, X):
        """Get probability estimates via voting"""
        tree_predictions = np.array([tree.predict(X) for tree in self.trees])
        n_samples = X.shape[0]
        n_classes = len(np.unique(tree_predictions))

        probabilities = np.zeros((n_samples, n_classes))

        for i in range(n_samples):
            votes = Counter(tree_predictions[:, i])
            for class_val, count in votes.items():
                probabilities[i, class_val] = count / self.n_trees

        return probabilities

def demonstrate_random_forest():
    """Demonstrate Random Forest"""
    print("=== Random Forest Classifier Demo ===\n")

    # Generate complex dataset
    np.random.seed(42)

    # Three classes with overlap
    n_samples = 100
    X0 = np.random.randn(n_samples, 2) + np.array([2, 2])
    X1 = np.random.randn(n_samples, 2) + np.array([6, 6])
    X2 = np.random.randn(n_samples, 2) + np.array([4, 2])

    X = np.vstack([X0, X1, X2])
    y = np.array([0]*n_samples + [1]*n_samples + [2]*n_samples)

    # Shuffle
    indices = np.random.permutation(len(X))
    X = X[indices]
    y = y[indices]

    # Split train/test
    split = int(0.8 * len(X))
    X_train, y_train = X[:split], y[:split]
    X_test, y_test = X[split:], y[split:]

    # Train Random Forest
    print("Training Random Forest (100 trees)...")
    rf = RandomForestClassifier(n_trees=100, max_depth=10)
    rf.fit(X_train, y_train)

    # Evaluate
    train_pred = rf.predict(X_train)
    test_pred = rf.predict(X_test)

    train_acc = np.mean(train_pred == y_train)
    test_acc = np.mean(test_pred == y_test)

    print(f"\n📊 Results:")
    print(f"   Training Accuracy: {train_acc*100:.2f}%")
    print(f"   Test Accuracy: {test_acc*100:.2f}%")

    # Visualize
    plt.figure(figsize=(15, 5))

    # Plot 1: Training data
    plt.subplot(1, 3, 1)
    colors = ['red', 'blue', 'green']
    for class_val in range(3):
        mask = y_train == class_val
        plt.scatter(X_train[mask, 0], X_train[mask, 1],
                   color=colors[class_val], alpha=0.6,
                   label=f'Class {class_val}', s=50)
    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.title('Training Data')
    plt.legend()
    plt.grid(True, alpha=0.3)

    # Plot 2: Decision boundary
    plt.subplot(1, 3, 2)
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    xx, yy = np.meshgrid(np.linspace(x_min, x_max, 200),
                         np.linspace(y_min, y_max, 200))

    Z = rf.predict(np.c_[xx.ravel(), yy.ravel()])
    Z = Z.reshape(xx.shape)

    plt.contourf(xx, yy, Z, alpha=0.3, cmap='RdYlGn')

    for class_val in range(3):
        mask = y_test == class_val
        plt.scatter(X_test[mask, 0], X_test[mask, 1],
                   color=colors[class_val], alpha=0.8,
                   label=f'Class {class_val}', s=50, edgecolors='black')

    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.title('Random Forest Decision Boundary')
    plt.legend()
    plt.grid(True, alpha=0.3)

    # Plot 3: Forest visualization concept
    plt.subplot(1, 3, 3)
    plt.text(0.5, 0.95, '🌲 Random Forest Concept',
             ha='center', va='top', fontsize=14, weight='bold',
             transform=plt.gca().transAxes)

    concept_text = """
    Bootstrap Samples:
    Tree 1: [Sample 1, 5, 7, 2, ...]
    Tree 2: [Sample 3, 3, 8, 1, ...]
    Tree 3: [Sample 2, 9, 4, 5, ...]
    ...

    Each tree trained on:
    • Random bootstrap sample
    • Random feature subset

    Prediction = Majority Vote:
    Tree 1 → Class 0
    Tree 2 → Class 1
    Tree 3 → Class 0
    ...
    Final: Class 0 (most votes)
    """

    plt.text(0.1, 0.8, concept_text, ha='left', va='top',
             fontsize=9, family='monospace',
             transform=plt.gca().transAxes)
    plt.axis('off')

    plt.tight_layout()
    plt.savefig('random_forest_results.png')
    print("\n📊 Results saved to 'random_forest_results.png'")

    # Explain concepts
    print("\n📚 Random Forest Concepts:")
    print("   • Ensemble Learning: Combine multiple models")
    print("   • Bagging: Bootstrap Aggregating")
    print("   • Random Features: Each split uses random subset")
    print("   • Voting: Final prediction from majority vote")
    print("   • Out-of-Bag Score: Use unused samples for validation")

    print("\n🎯 Advantages:")
    print("   ✓ Reduces overfitting vs single tree")
    print("   ✓ More accurate and stable")
    print("   ✓ Works well with high-dimensional data")
    print("   ✓ Provides feature importance")
    print("   ✓ Handles missing values")

    print("\n⚙️  Hyperparameters:")
    print("   • n_trees: More trees → better but slower")
    print("   • max_depth: Controls tree complexity")
    print("   • n_features: sqrt(n) for classification")
    print("   • min_samples_split: Controls tree growth")

if __name__ == "__main__":
    demonstrate_random_forest()
    print("\n✅ Random Forest Demo Complete!")
