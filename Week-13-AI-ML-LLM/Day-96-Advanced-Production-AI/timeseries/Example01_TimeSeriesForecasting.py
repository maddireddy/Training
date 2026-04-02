"""
Time Series Forecasting with LSTM
Demonstrates: Sequential prediction, temporal patterns, forecasting future values
Real-world application: Stock prices, sales forecasting, demand prediction
"""

import numpy as np
import matplotlib.pyplot as plt

class TimeSeriesLSTM:
    """
    LSTM for Time Series Forecasting
    Predicts future values based on historical patterns

    Applications:
    - Stock price prediction
    - Sales forecasting
    - Energy demand prediction
    - Weather forecasting
    - Traffic flow prediction
    """
    def __init__(self, hidden_size=50, sequence_length=10):
        """
        Parameters:
        hidden_size: Number of LSTM units
        sequence_length: Number of past timesteps to consider
        """
        self.hidden_size = hidden_size
        self.sequence_length = sequence_length

        # LSTM weights (simplified)
        self.W_f = np.random.randn(hidden_size + 1, hidden_size) * 0.01
        self.W_i = np.random.randn(hidden_size + 1, hidden_size) * 0.01
        self.W_c = np.random.randn(hidden_size + 1, hidden_size) * 0.01
        self.W_o = np.random.randn(hidden_size + 1, hidden_size) * 0.01

        # Output layer
        self.W_out = np.random.randn(hidden_size, 1) * 0.01
        self.b_out = 0

        self.loss_history = []

    def sigmoid(self, x):
        """Sigmoid activation"""
        return 1 / (1 + np.exp(-np.clip(x, -500, 500)))

    def tanh(self, x):
        """Tanh activation"""
        return np.tanh(np.clip(x, -500, 500))

    def lstm_cell(self, x_t, h_prev, c_prev):
        """
        LSTM cell forward pass

        Time series specific:
        - Learns seasonal patterns
        - Captures trends
        - Handles varying time scales
        """
        # Concatenate input and hidden state
        concat = np.concatenate([x_t.reshape(1, -1), h_prev], axis=1)

        # Gates
        f_t = self.sigmoid(np.dot(concat, self.W_f))  # Forget gate
        i_t = self.sigmoid(np.dot(concat, self.W_i))  # Input gate
        c_tilde = self.tanh(np.dot(concat, self.W_c))  # Cell candidate
        o_t = self.sigmoid(np.dot(concat, self.W_o))  # Output gate

        # Cell state and hidden state
        c_t = f_t * c_prev + i_t * c_tilde
        h_t = o_t * self.tanh(c_t)

        return h_t, c_t

    def forward(self, sequence):
        """
        Process entire sequence

        sequence: Array of shape (sequence_length, 1)
        """
        h_t = np.zeros((1, self.hidden_size))
        c_t = np.zeros((1, self.hidden_size))

        # Process sequence
        for x_t in sequence:
            h_t, c_t = self.lstm_cell(x_t, h_t, c_t)

        # Final prediction
        prediction = np.dot(h_t, self.W_out) + self.b_out

        return prediction[0, 0]

    def create_sequences(self, data):
        """
        Create input-output pairs for training

        Example: [1,2,3,4,5,6,7,8,9,10]
        - Input: [1,2,3,4,5] → Output: 6
        - Input: [2,3,4,5,6] → Output: 7
        - Input: [3,4,5,6,7] → Output: 8
        """
        X, y = [], []

        for i in range(len(data) - self.sequence_length):
            X.append(data[i:i + self.sequence_length])
            y.append(data[i + self.sequence_length])

        return np.array(X), np.array(y)

    def train(self, data, epochs=100, learning_rate=0.001):
        """
        Train on time series data
        """
        # Create sequences
        X, y = self.create_sequences(data)

        print(f"Training on {len(X)} sequences...")

        for epoch in range(epochs):
            total_loss = 0

            for seq, target in zip(X, y):
                # Forward pass
                prediction = self.forward(seq)

                # Loss (MSE)
                loss = (prediction - target) ** 2
                total_loss += loss

                # Simple gradient descent (simplified)
                error = prediction - target
                self.W_out -= learning_rate * error
                self.b_out -= learning_rate * error

            avg_loss = total_loss / len(X)
            self.loss_history.append(avg_loss)

            if (epoch + 1) % 20 == 0:
                print(f"  Epoch {epoch + 1}: Loss = {avg_loss:.6f}")

    def predict_future(self, initial_sequence, n_steps):
        """
        Forecast future values

        initial_sequence: Last known values
        n_steps: Number of steps to predict
        """
        predictions = []
        current_sequence = initial_sequence.copy()

        for _ in range(n_steps):
            # Predict next value
            next_value = self.forward(current_sequence[-self.sequence_length:])
            predictions.append(next_value)

            # Update sequence
            current_sequence = np.append(current_sequence, next_value)

        return np.array(predictions)

def generate_synthetic_timeseries():
    """
    Generate synthetic time series with:
    - Trend
    - Seasonality
    - Noise
    """
    np.random.seed(42)

    n_points = 200
    time = np.arange(n_points)

    # Components
    trend = 0.05 * time  # Upward trend
    seasonality = 10 * np.sin(2 * np.pi * time / 20)  # Seasonal pattern
    noise = np.random.randn(n_points) * 2  # Random noise

    # Combine
    data = trend + seasonality + noise + 50  # Base value of 50

    return data

def demonstrate_time_series_forecasting():
    """
    Demonstrate time series forecasting with LSTM
    """
    print("=" * 70)
    print("⏰ TIME SERIES FORECASTING WITH LSTM")
    print("=" * 70)

    # Generate synthetic data
    data = generate_synthetic_timeseries()

    print(f"\n📊 Dataset:")
    print(f"   Total points: {len(data)}")
    print(f"   Min value: {data.min():.2f}")
    print(f"   Max value: {data.max():.2f}")
    print(f"   Mean: {data.mean():.2f}")

    # Normalize data (important for LSTM)
    data_mean = data.mean()
    data_std = data.std()
    normalized_data = (data - data_mean) / data_std

    # Split train/test
    train_size = int(0.8 * len(normalized_data))
    train_data = normalized_data[:train_size]
    test_data = normalized_data[train_size:]

    print(f"\n📈 Data Split:")
    print(f"   Training samples: {len(train_data)}")
    print(f"   Test samples: {len(test_data)}")

    # Create and train model
    model = TimeSeriesLSTM(hidden_size=20, sequence_length=10)

    print(f"\n🧠 Training LSTM Model...")
    model.train(train_data, epochs=100, learning_rate=0.001)

    # Make predictions
    print(f"\n🔮 Making Predictions...")

    # Predict on test data
    test_predictions = []
    for i in range(len(test_data) - model.sequence_length):
        seq = test_data[i:i + model.sequence_length]
        pred = model.forward(seq)
        test_predictions.append(pred)

    # Denormalize predictions
    test_predictions = np.array(test_predictions) * data_std + data_mean
    actual_test = test_data[model.sequence_length:] * data_std + data_mean

    # Calculate metrics
    mae = np.mean(np.abs(test_predictions - actual_test))
    rmse = np.sqrt(np.mean((test_predictions - actual_test) ** 2))

    print(f"\n📊 Evaluation Metrics:")
    print(f"   MAE (Mean Absolute Error): {mae:.4f}")
    print(f"   RMSE (Root Mean Squared Error): {rmse:.4f}")

    # Forecast future
    print(f"\n🔮 Forecasting Next 20 Steps...")
    future_predictions = model.predict_future(test_data, n_steps=20)
    future_predictions = future_predictions * data_std + data_mean

    # Visualize
    plt.figure(figsize=(16, 10))

    # Plot 1: Original time series with components
    plt.subplot(3, 2, 1)
    time = np.arange(len(data))
    plt.plot(time, data, color='blue', alpha=0.7, linewidth=1)
    plt.axvline(train_size, color='red', linestyle='--', label='Train/Test Split')
    plt.xlabel('Time')
    plt.ylabel('Value')
    plt.title('Original Time Series')
    plt.legend()
    plt.grid(True, alpha=0.3)

    # Plot 2: Training loss
    plt.subplot(3, 2, 2)
    plt.plot(model.loss_history, color='green', linewidth=2)
    plt.xlabel('Epoch')
    plt.ylabel('Loss (MSE)')
    plt.title('Training Loss Convergence')
    plt.grid(True, alpha=0.3)

    # Plot 3: Predictions vs Actual
    plt.subplot(3, 2, 3)
    test_time = np.arange(train_size + model.sequence_length,
                          train_size + model.sequence_length + len(test_predictions))
    plt.plot(test_time, actual_test, label='Actual', color='blue', linewidth=2)
    plt.plot(test_time, test_predictions, label='Predicted',
             color='red', linewidth=2, linestyle='--')
    plt.xlabel('Time')
    plt.ylabel('Value')
    plt.title('Test Set: Predictions vs Actual')
    plt.legend()
    plt.grid(True, alpha=0.3)

    # Plot 4: Forecast
    plt.subplot(3, 2, 4)
    # Plot historical data
    plt.plot(time, data, color='blue', alpha=0.5, label='Historical')
    # Plot forecast
    forecast_time = np.arange(len(data), len(data) + len(future_predictions))
    plt.plot(forecast_time, future_predictions, color='red',
             linewidth=2, marker='o', label='Forecast')
    plt.axvline(len(data), color='green', linestyle='--', label='Forecast Start')
    plt.xlabel('Time')
    plt.ylabel('Value')
    plt.title('Future Forecast (Next 20 Steps)')
    plt.legend()
    plt.grid(True, alpha=0.3)

    # Plot 5: Time Series Components
    plt.subplot(3, 2, 5)
    plt.text(0.1, 0.9, 'Time Series Components:',
             fontsize=12, weight='bold', transform=plt.gca().transAxes)

    components_text = """
    Original = Trend + Seasonality + Noise

    1️⃣ TREND:
       • Long-term direction (up/down)
       • Linear or non-linear growth

    2️⃣ SEASONALITY:
       • Repeating patterns
       • Daily, weekly, monthly, yearly

    3️⃣ NOISE:
       • Random fluctuations
       • Unpredictable variations

    LSTM learns to:
    ✓ Capture trends
    ✓ Identify seasonal patterns
    ✓ Filter noise
    ✓ Make multi-step predictions

    Applications:
    • Stock price prediction
    • Sales forecasting
    • Energy demand
    • Weather forecasting
    • Traffic prediction
    """

    plt.text(0.1, 0.8, components_text, fontsize=9,
             family='monospace', transform=plt.gca().transAxes)
    plt.axis('off')

    # Plot 6: LSTM Architecture for Time Series
    plt.subplot(3, 2, 6)
    plt.text(0.5, 0.98, 'LSTM for Time Series',
             fontsize=12, weight='bold', ha='center', transform=plt.gca().transAxes)

    architecture = """
    Time:   t-2      t-1       t      t+1
             ↓        ↓        ↓       ↓
    ┌─────────────────────────────────────┐
    │     SEQUENTIAL PROCESSING           │
    │                                     │
    │   [LSTM] → [LSTM] → [LSTM] → [LSTM]│
    │      ↓        ↓        ↓        ↓   │
    │   Hidden  Hidden  Hidden  Predict  │
    │   State   State   State   Future   │
    │                                     │
    │  Memory cells remember:             │
    │  • Recent trends                    │
    │  • Seasonal patterns                │
    │  • Long-term dependencies           │
    │                                     │
    │  Gates control:                     │
    │  • What to forget                   │
    │  • What to remember                 │
    │  • What to output                   │
    └─────────────────────────────────────┘

    Key Advantages:
    ✓ Handles variable-length sequences
    ✓ Learns long-term dependencies
    ✓ Captures non-linear patterns
    ✓ Multi-step ahead forecasting
    """

    plt.text(0.05, 0.90, architecture, fontsize=8,
             family='monospace', transform=plt.gca().transAxes)
    plt.axis('off')

    plt.tight_layout()
    plt.savefig('time_series_forecasting.png', dpi=150)
    print(f"\n📊 Visualization saved to 'time_series_forecasting.png'")

    # Best practices
    print(f"\n💡 Time Series Forecasting Best Practices:")
    print(f"   1. DATA PREPARATION:")
    print(f"      • Handle missing values")
    print(f"      • Normalize/standardize data")
    print(f"      • Check for stationarity")
    print(f"      • Remove outliers")

    print(f"\n   2. FEATURE ENGINEERING:")
    print(f"      • Lag features (t-1, t-2, ...)")
    print(f"      • Rolling statistics (moving average)")
    print(f"      • Time-based features (day, month, year)")
    print(f"      • Fourier features for seasonality")

    print(f"\n   3. MODEL SELECTION:")
    print(f"      • Simple: ARIMA, Exponential Smoothing")
    print(f"      • ML: Random Forest, XGBoost")
    print(f"      • Deep Learning: LSTM, GRU, Transformer")

    print(f"\n   4. EVALUATION:")
    print(f"      • Use walk-forward validation")
    print(f"      • Multiple metrics (MAE, RMSE, MAPE)")
    print(f"      • Test on unseen data")
    print(f"      • Check residuals")

    print(f"\n   5. PRODUCTION CONSIDERATIONS:")
    print(f"      • Real-time prediction latency")
    print(f"      • Model retraining frequency")
    print(f"      • Data drift monitoring")
    print(f"      • Prediction intervals (uncertainty)")

    print(f"\n🎯 Real-World Applications:")
    print(f"   • 📈 Stock Market: Price prediction, trading signals")
    print(f"   • 🛒 Retail: Demand forecasting, inventory optimization")
    print(f"   • ⚡ Energy: Load forecasting, grid management")
    print(f"   • 🌤️  Weather: Temperature, precipitation prediction")
    print(f"   • 🚗 Traffic: Flow prediction, route optimization")
    print(f"   • 🏥 Healthcare: Patient admissions, disease spread")

if __name__ == "__main__":
    demonstrate_time_series_forecasting()
    print("\n" + "=" * 70)
    print("✅ TIME SERIES FORECASTING DEMO COMPLETE!")
    print("=" * 70)
