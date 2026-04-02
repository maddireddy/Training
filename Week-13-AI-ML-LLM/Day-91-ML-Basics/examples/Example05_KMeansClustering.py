"""
K-Means Clustering
Demonstrates: Unsupervised learning, centroid-based clustering
"""

import numpy as np
import matplotlib.pyplot as plt

class KMeans:
    """
    K-Means Clustering Algorithm
    Unsupervised learning - finds patterns without labels
    """
    def __init__(self, n_clusters=3, max_iters=100, random_state=None):
        """
        Parameters:
        n_clusters: Number of clusters (K)
        max_iters: Maximum iterations
        random_state: Random seed for reproducibility
        """
        self.n_clusters = n_clusters
        self.max_iters = max_iters
        self.random_state = random_state
        self.centroids = None
        self.labels = None
        self.inertia_history = []

    def initialize_centroids(self, X):
        """
        Initialize centroids randomly from data points
        (K-Means++ initialization for better results)
        """
        if self.random_state is not None:
            np.random.seed(self.random_state)

        n_samples = X.shape[0]

        # K-Means++ initialization
        centroids = []

        # Choose first centroid randomly
        centroids.append(X[np.random.randint(n_samples)])

        # Choose remaining centroids
        for _ in range(1, self.n_clusters):
            # Calculate distances to nearest centroid
            distances = np.array([
                min(np.linalg.norm(x - c)**2 for c in centroids)
                for x in X
            ])

            # Choose next centroid with probability proportional to distance
            probabilities = distances / distances.sum()
            cumulative_probs = probabilities.cumsum()
            r = np.random.rand()

            for idx, cum_prob in enumerate(cumulative_probs):
                if r < cum_prob:
                    centroids.append(X[idx])
                    break

        return np.array(centroids)

    def assign_clusters(self, X):
        """
        Assign each point to nearest centroid
        Distance metric: Euclidean distance
        """
        distances = np.zeros((X.shape[0], self.n_clusters))

        for k in range(self.n_clusters):
            distances[:, k] = np.linalg.norm(X - self.centroids[k], axis=1)

        return np.argmin(distances, axis=1)

    def update_centroids(self, X, labels):
        """
        Update centroids as mean of assigned points
        """
        centroids = np.zeros((self.n_clusters, X.shape[1]))

        for k in range(self.n_clusters):
            cluster_points = X[labels == k]
            if len(cluster_points) > 0:
                centroids[k] = cluster_points.mean(axis=0)
            else:
                # If cluster is empty, reinitialize randomly
                centroids[k] = X[np.random.randint(X.shape[0])]

        return centroids

    def compute_inertia(self, X, labels):
        """
        Compute inertia (within-cluster sum of squares)
        Lower inertia = tighter clusters
        """
        inertia = 0
        for k in range(self.n_clusters):
            cluster_points = X[labels == k]
            if len(cluster_points) > 0:
                inertia += np.sum((cluster_points - self.centroids[k])**2)
        return inertia

    def fit(self, X):
        """
        Train K-Means clustering

        Algorithm:
        1. Initialize K centroids
        2. Assign points to nearest centroid
        3. Update centroids as mean of assigned points
        4. Repeat until convergence
        """
        # Initialize centroids
        self.centroids = self.initialize_centroids(X)

        for iteration in range(self.max_iters):
            # Assign clusters
            old_centroids = self.centroids.copy()
            self.labels = self.assign_clusters(X)

            # Update centroids
            self.centroids = self.update_centroids(X, self.labels)

            # Compute inertia
            inertia = self.compute_inertia(X, self.labels)
            self.inertia_history.append(inertia)

            # Check convergence
            if np.allclose(old_centroids, self.centroids):
                print(f"Converged at iteration {iteration + 1}")
                break

            if (iteration + 1) % 10 == 0:
                print(f"Iteration {iteration + 1}: Inertia = {inertia:.2f}")

    def predict(self, X):
        """Predict cluster for new data"""
        return self.assign_clusters(X)

def demonstrate_kmeans():
    """Demonstrate K-Means clustering"""
    print("=== K-Means Clustering Demo ===\n")

    # Generate sample data (3 clusters)
    np.random.seed(42)

    cluster1 = np.random.randn(100, 2) + np.array([2, 2])
    cluster2 = np.random.randn(100, 2) + np.array([8, 8])
    cluster3 = np.random.randn(100, 2) + np.array([2, 8])

    X = np.vstack([cluster1, cluster2, cluster3])

    # Shuffle
    indices = np.random.permutation(len(X))
    X = X[indices]

    # Train K-Means
    print("Training K-Means (K=3)...")
    kmeans = KMeans(n_clusters=3, max_iters=100, random_state=42)
    kmeans.fit(X)

    labels = kmeans.labels
    centroids = kmeans.centroids

    print(f"\n✅ Final Inertia: {kmeans.inertia_history[-1]:.2f}")

    # Visualize
    plt.figure(figsize=(15, 5))

    # Plot 1: Original data (unlabeled)
    plt.subplot(1, 3, 1)
    plt.scatter(X[:, 0], X[:, 1], c='gray', alpha=0.5, s=30)
    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.title('Original Data (Unlabeled)')
    plt.grid(True, alpha=0.3)

    # Plot 2: Clustered data
    plt.subplot(1, 3, 2)
    colors = ['red', 'blue', 'green']
    for k in range(3):
        cluster_points = X[labels == k]
        plt.scatter(cluster_points[:, 0], cluster_points[:, 1],
                   c=colors[k], alpha=0.6, s=30, label=f'Cluster {k}')

    plt.scatter(centroids[:, 0], centroids[:, 1],
               c='black', marker='X', s=200, linewidths=2,
               edgecolors='yellow', label='Centroids')

    plt.xlabel('Feature 1')
    plt.ylabel('Feature 2')
    plt.title('K-Means Clustering Result')
    plt.legend()
    plt.grid(True, alpha=0.3)

    # Plot 3: Convergence (Inertia over iterations)
    plt.subplot(1, 3, 3)
    plt.plot(kmeans.inertia_history, color='purple', linewidth=2)
    plt.xlabel('Iteration')
    plt.ylabel('Inertia (Within-cluster Sum of Squares)')
    plt.title('K-Means Convergence')
    plt.grid(True, alpha=0.3)

    plt.tight_layout()
    plt.savefig('kmeans_clustering_results.png')
    print("\n📊 Results saved to 'kmeans_clustering_results.png'")

    # Demonstrate elbow method
    print("\n🔍 Finding Optimal K (Elbow Method)...")
    inertias = []
    K_range = range(1, 10)

    for k in K_range:
        kmeans_temp = KMeans(n_clusters=k, max_iters=50, random_state=42)
        kmeans_temp.fit(X)
        inertias.append(kmeans_temp.inertia_history[-1])

    plt.figure(figsize=(8, 5))
    plt.plot(K_range, inertias, 'bo-', linewidth=2, markersize=8)
    plt.xlabel('Number of Clusters (K)')
    plt.ylabel('Inertia')
    plt.title('Elbow Method for Optimal K')
    plt.grid(True, alpha=0.3)
    plt.savefig('elbow_method.png')
    print("📊 Elbow plot saved to 'elbow_method.png'")

    # Explain concepts
    print("\n📚 K-Means Concepts:")
    print("   • Unsupervised Learning: No labels needed")
    print("   • Centroid: Center point of a cluster")
    print("   • Inertia: Sum of squared distances to centroids")
    print("   • Convergence: When centroids stop moving")
    print("   • K-Means++: Smart initialization method")

    print("\n🎯 Algorithm Steps:")
    print("   1. Initialize K centroids (randomly or K-Means++)")
    print("   2. Assign each point to nearest centroid")
    print("   3. Update centroids as mean of assigned points")
    print("   4. Repeat steps 2-3 until convergence")

    print("\n⚙️  Choosing K (Number of Clusters):")
    print("   • Elbow Method: Plot inertia vs K, find 'elbow'")
    print("   • Silhouette Score: Measure cluster quality")
    print("   • Domain Knowledge: Use business context")
    print("   • Gap Statistic: Compare with random data")

    print("\n🎯 Advantages:")
    print("   ✓ Simple and fast")
    print("   ✓ Works well with spherical clusters")
    print("   ✓ Scales to large datasets")

    print("\n⚠️  Limitations:")
    print("   ✗ Must specify K in advance")
    print("   ✗ Sensitive to initialization")
    print("   ✗ Assumes spherical clusters")
    print("   ✗ Affected by outliers")
    print("   ✗ Not good for clusters of different sizes/densities")

    print("\n💡 Applications:")
    print("   • Customer Segmentation")
    print("   • Image Compression")
    print("   • Document Clustering")
    print("   • Anomaly Detection")
    print("   • Feature Engineering")

if __name__ == "__main__":
    demonstrate_kmeans()
    print("\n✅ K-Means Clustering Demo Complete!")
