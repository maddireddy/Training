"""
Convolutional Neural Network (CNN) Concepts
Demonstrates: Convolution, pooling, CNN architecture
"""

import numpy as np
import matplotlib.pyplot as plt

class ConvolutionalLayer:
    """
    Simple 2D Convolutional Layer
    Demonstrates convolution operation
    """
    def __init__(self, num_filters, filter_size):
        self.num_filters = num_filters
        self.filter_size = filter_size
        # Initialize filters with random values
        self.filters = np.random.randn(num_filters, filter_size, filter_size) * 0.1

    def conv_operation(self, image, kernel):
        """Perform 2D convolution"""
        img_h, img_w = image.shape
        kernel_h, kernel_w = kernel.shape

        output_h = img_h - kernel_h + 1
        output_w = img_w - kernel_w + 1

        output = np.zeros((output_h, output_w))

        for i in range(output_h):
            for j in range(output_w):
                region = image[i:i+kernel_h, j:j+kernel_w]
                output[i, j] = np.sum(region * kernel)

        return output

    def forward(self, input_image):
        """Apply all filters to input"""
        outputs = []
        for f in range(self.num_filters):
            output = self.conv_operation(input_image, self.filters[f])
            outputs.append(output)

        return np.array(outputs)

    def visualize_filters(self):
        """Visualize learned filters"""
        fig, axes = plt.subplots(1, self.num_filters, figsize=(12, 3))

        for i in range(self.num_filters):
            if self.num_filters == 1:
                ax = axes
            else:
                ax = axes[i]
            ax.imshow(self.filters[i], cmap='gray')
            ax.set_title(f'Filter {i+1}')
            ax.axis('off')

        plt.tight_layout()
        plt.savefig('cnn_filters.png')
        print("📊 Filters saved to 'cnn_filters.png'")

class MaxPooling:
    """
    Max Pooling Layer
    Reduces spatial dimensions
    """
    def __init__(self, pool_size=2):
        self.pool_size = pool_size

    def forward(self, input_feature_map):
        """Apply max pooling"""
        h, w = input_feature_map.shape
        pool_h = h // self.pool_size
        pool_w = w // self.pool_size

        output = np.zeros((pool_h, pool_w))

        for i in range(pool_h):
            for j in range(pool_w):
                region = input_feature_map[
                    i*self.pool_size:(i+1)*self.pool_size,
                    j*self.pool_size:(j+1)*self.pool_size
                ]
                output[i, j] = np.max(region)

        return output

def demonstrate_cnn_concepts():
    """
    Demonstrate CNN operations
    """
    print("=== CNN Concepts Demonstration ===\n")

    # Create sample image (simple pattern)
    image = np.zeros((28, 28))
    # Vertical line
    image[:, 10:12] = 1
    # Horizontal line
    image[10:12, :] = 1

    print("1️⃣ Input Image (28x28)")
    print(f"   Shape: {image.shape}")

    # Define edge detection filters
    vertical_edge = np.array([[-1, 0, 1],
                              [-1, 0, 1],
                              [-1, 0, 1]])

    horizontal_edge = np.array([[-1, -1, -1],
                                [0, 0, 0],
                                [1, 1, 1]])

    # Apply convolution
    conv_layer = ConvolutionalLayer(num_filters=2, filter_size=3)
    conv_layer.filters[0] = vertical_edge
    conv_layer.filters[1] = horizontal_edge

    feature_maps = conv_layer.forward(image)
    print(f"\n2️⃣ After Convolution")
    print(f"   Number of feature maps: {len(feature_maps)}")
    print(f"   Feature map shape: {feature_maps[0].shape}")

    # Apply max pooling
    pooling = MaxPooling(pool_size=2)
    pooled_maps = []
    for fm in feature_maps:
        pooled = pooling.forward(fm)
        pooled_maps.append(pooled)

    print(f"\n3️⃣ After Max Pooling (2x2)")
    print(f"   Pooled feature map shape: {pooled_maps[0].shape}")

    # Visualize
    fig, axes = plt.subplots(2, 3, figsize=(12, 8))

    # Original image
    axes[0, 0].imshow(image, cmap='gray')
    axes[0, 0].set_title('Original Image')
    axes[0, 0].axis('off')

    # Filters
    axes[0, 1].imshow(vertical_edge, cmap='gray')
    axes[0, 1].set_title('Vertical Edge Filter')
    axes[0, 1].axis('off')

    axes[0, 2].imshow(horizontal_edge, cmap='gray')
    axes[0, 2].set_title('Horizontal Edge Filter')
    axes[0, 2].axis('off')

    # Feature maps after convolution
    axes[1, 0].imshow(feature_maps[0], cmap='hot')
    axes[1, 0].set_title('Vertical Features')
    axes[1, 0].axis('off')

    axes[1, 1].imshow(feature_maps[1], cmap='hot')
    axes[1, 1].set_title('Horizontal Features')
    axes[1, 1].axis('off')

    # Pooled feature map
    axes[1, 2].imshow(pooled_maps[0], cmap='hot')
    axes[1, 2].set_title('After Pooling')
    axes[1, 2].axis('off')

    plt.tight_layout()
    plt.savefig('cnn_operations.png')
    print("\n📊 CNN operations saved to 'cnn_operations.png'")

    # Architecture summary
    print("\n📐 Typical CNN Architecture:")
    print("   Input (28x28x1)")
    print("   ↓")
    print("   Conv2D (3x3) → 26x26x32")
    print("   ↓")
    print("   ReLU → 26x26x32")
    print("   ↓")
    print("   MaxPool (2x2) → 13x13x32")
    print("   ↓")
    print("   Conv2D (3x3) → 11x11x64")
    print("   ↓")
    print("   ReLU → 11x11x64")
    print("   ↓")
    print("   MaxPool (2x2) → 5x5x64")
    print("   ↓")
    print("   Flatten → 1600")
    print("   ↓")
    print("   Dense (128) → 128")
    print("   ↓")
    print("   ReLU → 128")
    print("   ↓")
    print("   Dense (10) → 10 (Output)")
    print("   ↓")
    print("   Softmax → Probabilities")

if __name__ == "__main__":
    demonstrate_cnn_concepts()
    print("\n✅ CNN Concepts Demo Complete!")
