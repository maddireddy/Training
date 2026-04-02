"""
Production AI/ML Deployment Guide
Demonstrates: End-to-end deployment, monitoring, and responsible AI practices

Critical for: High-risk NLP applications with regulatory compliance, privacy, safety
Real-world: Production-grade AI systems at scale
"""

from dataclasses import dataclass
from typing import List, Dict, Any, Optional
from enum import Enum
import time
from datetime import datetime
import json

class RiskLevel(Enum):
    """Risk levels for AI applications"""
    LOW = "low"
    MEDIUM = "medium"
    HIGH = "high"
    CRITICAL = "critical"

@dataclass
class ModelMetadata:
    """
    Production model metadata

    Critical for: Compliance, auditing, versioning
    """
    model_id: str
    version: str
    created_at: datetime
    created_by: str
    training_data_info: Dict[str, Any]
    performance_metrics: Dict[str, float]
    risk_level: RiskLevel
    compliance_certifications: List[str]

@dataclass
class PredictionRequest:
    """Input to model"""
    request_id: str
    input_data: Any
    user_id: Optional[str] = None
    context: Optional[Dict] = None

@dataclass
class PredictionResponse:
    """Model output with metadata"""
    request_id: str
    prediction: Any
    confidence: float
    model_version: str
    latency_ms: float
    explanation: Optional[str] = None
    safety_flags: List[str] = None

class SafetyGuardrails:
    """
    Safety checks for AI outputs

    Critical for: Preventing harm, bias, inappropriate content
    """
    @staticmethod
    def check_toxicity(text: str) -> Dict[str, Any]:
        """
        Check for toxic content

        Production: Use Perspective API, OpenAI Moderation API
        """
        # Simplified toxicity check (in production, use ML model)
        toxic_keywords = ['hate', 'violence', 'illegal', 'harmful']

        toxicity_score = sum(1 for word in toxic_keywords if word in text.lower())
        is_toxic = toxicity_score > 0

        return {
            'is_toxic': is_toxic,
            'toxicity_score': toxicity_score / len(toxic_keywords),
            'flags': [word for word in toxic_keywords if word in text.lower()]
        }

    @staticmethod
    def check_pii(text: str) -> Dict[str, Any]:
        """
        Check for Personally Identifiable Information

        Critical for: GDPR, HIPAA, privacy compliance
        """
        import re

        pii_patterns = {
            'email': r'\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}\b',
            'phone': r'\b\d{3}[-.]?\d{3}[-.]?\d{4}\b',
            'ssn': r'\b\d{3}-\d{2}-\d{4}\b',
            'credit_card': r'\b\d{4}[-\s]?\d{4}[-\s]?\d{4}[-\s]?\d{4}\b'
        }

        found_pii = {}
        for pii_type, pattern in pii_patterns.items():
            matches = re.findall(pattern, text)
            if matches:
                found_pii[pii_type] = matches

        return {
            'contains_pii': len(found_pii) > 0,
            'pii_types': list(found_pii.keys()),
            'details': found_pii
        }

    @staticmethod
    def check_bias(input_data: str, prediction: str) -> Dict[str, Any]:
        """
        Check for biased outputs

        Critical for: Fairness, anti-discrimination
        """
        # Simplified bias detection
        protected_attributes = ['race', 'gender', 'age', 'religion', 'disability']

        mentions = [attr for attr in protected_attributes
                   if attr in prediction.lower()]

        return {
            'potential_bias': len(mentions) > 0,
            'protected_attributes_mentioned': mentions,
            'requires_review': len(mentions) > 1
        }

    @staticmethod
    def check_factuality(text: str, knowledge_base: Optional[List[str]] = None) -> Dict[str, Any]:
        """
        Verify factual accuracy

        Production: Use fact-checking APIs, RAG systems
        """
        # Simplified fact check
        confidence_keywords = ['probably', 'maybe', 'might', 'could be', 'I think']

        hedging_count = sum(1 for keyword in confidence_keywords
                          if keyword in text.lower())

        return {
            'confidence_level': 'low' if hedging_count > 2 else 'high',
            'hedging_detected': hedging_count > 0,
            'requires_verification': hedging_count > 2
        }

class ModelMonitor:
    """
    Production model monitoring

    Tracks: Performance, drift, errors, usage
    """
    def __init__(self):
        self.metrics = {
            'total_requests': 0,
            'total_errors': 0,
            'avg_latency_ms': 0,
            'safety_violations': 0,
            'predictions_by_hour': {}
        }
        self.predictions_log = []

    def log_prediction(self, request: PredictionRequest,
                      response: PredictionResponse,
                      safety_checks: Dict[str, Any]):
        """
        Log prediction for monitoring and auditing

        Critical for: Debugging, compliance, improvement
        """
        self.metrics['total_requests'] += 1

        # Update average latency
        n = self.metrics['total_requests']
        old_avg = self.metrics['avg_latency_ms']
        self.metrics['avg_latency_ms'] = (
            (old_avg * (n - 1) + response.latency_ms) / n
        )

        # Track safety violations
        if safety_checks.get('violations'):
            self.metrics['safety_violations'] += 1

        # Store for audit trail
        self.predictions_log.append({
            'timestamp': datetime.now().isoformat(),
            'request_id': request.request_id,
            'user_id': request.user_id,
            'model_version': response.model_version,
            'confidence': response.confidence,
            'latency_ms': response.latency_ms,
            'safety_checks': safety_checks
        })

    def get_metrics(self) -> Dict[str, Any]:
        """Get current metrics"""
        return {
            **self.metrics,
            'error_rate': self.metrics['total_errors'] / max(1, self.metrics['total_requests']),
            'safety_violation_rate': self.metrics['safety_violations'] / max(1, self.metrics['total_requests'])
        }

    def detect_drift(self) -> Dict[str, Any]:
        """
        Detect model/data drift

        Production: Use statistical tests, ML-based detection
        """
        if len(self.predictions_log) < 100:
            return {'drift_detected': False, 'reason': 'Insufficient data'}

        # Simplified drift detection: check confidence distribution
        recent_predictions = self.predictions_log[-100:]
        old_predictions = self.predictions_log[-200:-100] if len(self.predictions_log) >= 200 else []

        if old_predictions:
            recent_confidence = sum(p['confidence'] for p in recent_predictions) / len(recent_predictions)
            old_confidence = sum(p['confidence'] for p in old_predictions) / len(old_predictions)

            confidence_drop = old_confidence - recent_confidence

            drift_detected = confidence_drop > 0.1  # 10% drop

            return {
                'drift_detected': drift_detected,
                'confidence_drop': confidence_drop,
                'recent_avg_confidence': recent_confidence,
                'baseline_avg_confidence': old_confidence,
                'action': 'RETRAIN_MODEL' if drift_detected else 'CONTINUE'
            }

        return {'drift_detected': False}

class ProductionMLSystem:
    """
    Complete production ML system

    Includes:
    - Model serving
    - Safety guardrails
    - Monitoring
    - Logging
    - A/B testing
    - Fallbacks
    """
    def __init__(self, model_metadata: ModelMetadata):
        self.model_metadata = model_metadata
        self.safety = SafetyGuardrails()
        self.monitor = ModelMonitor()
        self.model_loaded = True  # Simulated

    def predict(self, request: PredictionRequest) -> PredictionResponse:
        """
        Main prediction endpoint with full production safeguards
        """
        start_time = time.time()

        try:
            # Step 1: Input validation
            self._validate_input(request)

            # Step 2: Run safety checks on input
            input_safety = self._check_input_safety(request.input_data)

            if input_safety.get('block_request'):
                return self._create_error_response(
                    request,
                    "Request blocked by safety filters",
                    input_safety
                )

            # Step 3: Make prediction (simulated)
            prediction = self._run_model(request)

            # Step 4: Run safety checks on output
            output_safety = self._check_output_safety(
                request.input_data,
                prediction
            )

            # Step 5: Create response
            latency_ms = (time.time() - start_time) * 1000

            response = PredictionResponse(
                request_id=request.request_id,
                prediction=prediction,
                confidence=0.87,  # Simulated
                model_version=self.model_metadata.version,
                latency_ms=latency_ms,
                safety_flags=output_safety.get('flags', [])
            )

            # Step 6: Log for monitoring
            self.monitor.log_prediction(request, response, output_safety)

            # Step 7: Check for drift
            if self.monitor.metrics['total_requests'] % 100 == 0:
                drift_status = self.monitor.detect_drift()
                if drift_status['drift_detected']:
                    print(f"\n⚠️  ALERT: Model drift detected! {drift_status}")

            return response

        except Exception as e:
            self.monitor.metrics['total_errors'] += 1
            return self._create_error_response(request, str(e), {})

    def _validate_input(self, request: PredictionRequest):
        """Validate input format and content"""
        if not request.input_data:
            raise ValueError("Input data cannot be empty")

        if not isinstance(request.input_data, str):
            raise ValueError("Input must be string")

        if len(request.input_data) > 10000:
            raise ValueError("Input too long (max 10000 chars)")

    def _check_input_safety(self, input_data: str) -> Dict[str, Any]:
        """Safety checks on input"""
        toxicity = self.safety.check_toxicity(input_data)
        pii = self.safety.check_pii(input_data)

        violations = []

        if toxicity['is_toxic']:
            violations.append('toxic_content')

        if pii['contains_pii']:
            violations.append('pii_detected')

        return {
            'violations': violations,
            'block_request': len(violations) > 0,
            'details': {
                'toxicity': toxicity,
                'pii': pii
            }
        }

    def _check_output_safety(self, input_data: str, prediction: str) -> Dict[str, Any]:
        """Safety checks on model output"""
        toxicity = self.safety.check_toxicity(prediction)
        pii = self.safety.check_pii(prediction)
        bias = self.safety.check_bias(input_data, prediction)
        factuality = self.safety.check_factuality(prediction)

        flags = []

        if toxicity['is_toxic']:
            flags.append('toxic_output')

        if pii['contains_pii']:
            flags.append('pii_in_output')

        if bias['potential_bias']:
            flags.append('potential_bias')

        if factuality['requires_verification']:
            flags.append('low_factual_confidence')

        return {
            'flags': flags,
            'details': {
                'toxicity': toxicity,
                'pii': pii,
                'bias': bias,
                'factuality': factuality
            }
        }

    def _run_model(self, request: PredictionRequest) -> str:
        """
        Run actual model inference

        Production: Load model, run inference, return prediction
        """
        # Simulated model output
        return f"Response to: {request.input_data[:50]}..."

    def _create_error_response(self, request: PredictionRequest,
                               error_message: str,
                               details: Dict) -> PredictionResponse:
        """Create error response"""
        return PredictionResponse(
            request_id=request.request_id,
            prediction=f"Error: {error_message}",
            confidence=0.0,
            model_version=self.model_metadata.version,
            latency_ms=0,
            safety_flags=['error']
        )

def demonstrate_production_deployment():
    """
    Demonstrate complete production AI system
    """
    print("=" * 80)
    print("🏭 PRODUCTION AI/ML DEPLOYMENT SYSTEM")
    print("=" * 80)

    # Initialize production system
    model_metadata = ModelMetadata(
        model_id="sentiment-classifier-v2",
        version="2.1.0",
        created_at=datetime.now(),
        created_by="ml-team@company.com",
        training_data_info={
            'dataset_size': 1_000_000,
            'last_updated': '2024-01-15',
            'data_sources': ['customer_reviews', 'support_tickets']
        },
        performance_metrics={
            'accuracy': 0.94,
            'precision': 0.92,
            'recall': 0.91,
            'f1_score': 0.915
        },
        risk_level=RiskLevel.HIGH,
        compliance_certifications=['SOC2', 'GDPR', 'HIPAA']
    )

    system = ProductionMLSystem(model_metadata)

    print(f"\n📋 Model Metadata:")
    print(f"   ID: {model_metadata.model_id}")
    print(f"   Version: {model_metadata.version}")
    print(f"   Risk Level: {model_metadata.risk_level.value.upper()}")
    print(f"   F1 Score: {model_metadata.performance_metrics['f1_score']:.3f}")
    print(f"   Certifications: {', '.join(model_metadata.compliance_certifications)}")

    # Test cases
    print("\n" + "=" * 80)
    print("🧪 TESTING PRODUCTION SYSTEM")
    print("=" * 80)

    test_cases = [
        {
            'name': 'Normal request',
            'input': 'This product is amazing! I love it.'
        },
        {
            'name': 'Request with PII',
            'input': 'My email is john@example.com and phone is 555-123-4567'
        },
        {
            'name': 'Potentially toxic content',
            'input': 'This is hate speech and harmful content'
        },
        {
            'name': 'Request with bias concern',
            'input': 'Evaluate this candidate based on gender and age'
        }
    ]

    for i, test_case in enumerate(test_cases, 1):
        print(f"\n{'─' * 80}")
        print(f"Test {i}: {test_case['name']}")
        print(f"{'─' * 80}")

        request = PredictionRequest(
            request_id=f"req_{i}",
            input_data=test_case['input'],
            user_id=f"user_{i % 3}"
        )

        response = system.predict(request)

        print(f"\n📥 Input: {request.input_data}")
        print(f"📤 Prediction: {response.prediction}")
        print(f"   Confidence: {response.confidence:.2%}")
        print(f"   Latency: {response.latency_ms:.2f}ms")
        if response.safety_flags:
            print(f"   ⚠️  Safety Flags: {', '.join(response.safety_flags)}")

    # Show monitoring metrics
    print("\n" + "=" * 80)
    print("📊 MONITORING METRICS")
    print("=" * 80)

    metrics = system.monitor.get_metrics()

    print(f"\nTotal Requests: {metrics['total_requests']}")
    print(f"Total Errors: {metrics['total_errors']}")
    print(f"Error Rate: {metrics['error_rate']:.2%}")
    print(f"Avg Latency: {metrics['avg_latency_ms']:.2f}ms")
    print(f"Safety Violations: {metrics['safety_violations']}")
    print(f"Safety Violation Rate: {metrics['safety_violation_rate']:.2%}")

    # Production deployment architecture
    print("\n" + "=" * 80)
    print("🏗️  PRODUCTION DEPLOYMENT ARCHITECTURE")
    print("=" * 80)

    print("""
╔══════════════════════════════════════════════════════════════════════╗
║                  PRODUCTION ML SYSTEM ARCHITECTURE                   ║
╠══════════════════════════════════════════════════════════════════════╣
║                                                                       ║
║                          USER REQUEST                                ║
║                               ↓                                       ║
║                   ┌───────────────────────┐                          ║
║                   │   API GATEWAY         │                          ║
║                   │  • Authentication     │                          ║
║                   │  • Rate limiting      │                          ║
║                   │  • Load balancing     │                          ║
║                   └───────────────────────┘                          ║
║                               ↓                                       ║
║                   ┌───────────────────────┐                          ║
║                   │  INPUT VALIDATION     │                          ║
║                   │  • Schema check       │                          ║
║                   │  • Size limits        │                          ║
║                   │  • Format validation  │                          ║
║                   └───────────────────────┘                          ║
║                               ↓                                       ║
║                   ┌───────────────────────┐                          ║
║                   │  SAFETY GUARDRAILS    │  ◄─── CRITICAL           ║
║                   │  • Toxicity check     │                          ║
║                   │  • PII detection      │                          ║
║                   │  • Bias detection     │                          ║
║                   └───────────────────────┘                          ║
║                               ↓                                       ║
║              ┌─────────────────────────────────┐                     ║
║              │    MODEL SERVING                │                     ║
║              │  ┌────────────┐ ┌────────────┐ │                     ║
║              │  │  Model A   │ │  Model B   │ │  (A/B Testing)      ║
║              │  │  (90%)     │ │  (10%)     │ │                     ║
║              │  └────────────┘ └────────────┘ │                     ║
║              │                                 │                     ║
║              │  • GPU inference                │                     ║
║              │  • Batch processing             │                     ║
║              │  • Caching                      │                     ║
║              └─────────────────────────────────┘                     ║
║                               ↓                                       ║
║                   ┌───────────────────────┐                          ║
║                   │  OUTPUT VALIDATION    │                          ║
║                   │  • Safety checks      │                          ║
║                   │  • Fact verification  │                          ║
║                   │  • Quality control    │                          ║
║                   └───────────────────────┘                          ║
║                               ↓                                       ║
║          ┌──────────────────────────────────────┐                    ║
║          │       MONITORING & LOGGING           │                    ║
║          │  • Latency tracking                  │                    ║
║          │  • Error logging                     │                    ║
║          │  • Drift detection                   │                    ║
║          │  • Audit trail                       │                    ║
║          │  • Alerts & notifications            │                    ║
║          └──────────────────────────────────────┘                    ║
║                               ↓                                       ║
║                        RESPONSE TO USER                              ║
║                                                                       ║
║  DATA STORES:                                                        ║
║  • Vector DB (embeddings)                                            ║
║  • Cache (Redis)                                                     ║
║  • Logs (Elasticsearch)                                              ║
║  • Metrics (Prometheus)                                              ║
║  • Model registry (MLflow)                                           ║
║                                                                       ║
╚══════════════════════════════════════════════════════════════════════╝
    """)

    print("\n" + "=" * 80)
    print("✅ RESPONSIBLE AI CHECKLIST")
    print("=" * 80)

    print("""
┌──────────────────────────────────────────────────────────────────────┐
│                     RESPONSIBLE AI PRACTICES                         │
├──────────────────────────────────────────────────────────────────────┤
│                                                                       │
│  1️⃣  FAIRNESS & BIAS                                                 │
│     ✓ Test for demographic parity                                   │
│     ✓ Monitor outcomes across protected groups                       │
│     ✓ Regular bias audits                                            │
│     ✓ Diverse training data                                          │
│     ✓ Bias mitigation techniques                                     │
│                                                                       │
│  2️⃣  PRIVACY & DATA PROTECTION                                       │
│     ✓ GDPR/CCPA compliance                                           │
│     ✓ Data minimization                                              │
│     ✓ PII detection and masking                                      │
│     ✓ Differential privacy where applicable                          │
│     ✓ Right to deletion (forget)                                     │
│     ✓ Data retention policies                                        │
│                                                                       │
│  3️⃣  SAFETY & SECURITY                                               │
│     ✓ Content moderation (toxicity, hate speech)                     │
│     ✓ Adversarial robustness                                         │
│     ✓ Input/output validation                                        │
│     ✓ Rate limiting & abuse prevention                               │
│     ✓ Secure model deployment                                        │
│     ✓ Regular security audits                                        │
│                                                                       │
│  4️⃣  TRANSPARENCY & EXPLAINABILITY                                   │
│     ✓ Model cards (documentation)                                    │
│     ✓ Explainable predictions (SHAP, LIME)                           │
│     ✓ Clear limitations disclosure                                   │
│     ✓ Human oversight for critical decisions                         │
│     ✓ Audit trails                                                   │
│                                                                       │
│  5️⃣  RELIABILITY & ROBUSTNESS                                        │
│     ✓ Comprehensive testing                                          │
│     ✓ Monitoring & alerting                                          │
│     ✓ Graceful degradation                                           │
│     ✓ Drift detection & retraining                                   │
│     ✓ SLA guarantees                                                 │
│     ✓ Disaster recovery plans                                        │
│                                                                       │
│  6️⃣  COMPLIANCE & GOVERNANCE                                         │
│     ✓ Regulatory compliance (HIPAA, SOC2, etc.)                      │
│     ✓ Ethics review board                                            │
│     ✓ Model governance policies                                      │
│     ✓ Version control & rollback                                     │
│     ✓ Impact assessments                                             │
│                                                                       │
└──────────────────────────────────────────────────────────────────────┘
    """)

    print("\n💡 Production Best Practices:")

    print("\n📦 DEPLOYMENT:")
    print("   • Blue/Green deployments (zero downtime)")
    print("   • Canary releases (gradual rollout)")
    print("   • Feature flags (enable/disable)")
    print("   • Automated testing in CI/CD")
    print("   • Container orchestration (Kubernetes)")

    print("\n📊 MONITORING:")
    print("   • Latency (P50, P95, P99)")
    print("   • Error rates & types")
    print("   • Model drift (data & concept)")
    print("   • Business metrics (conversion, satisfaction)")
    print("   • Cost tracking (compute, API calls)")

    print("\n🔧 MLOPS TOOLS:")
    print("   • MLflow: Experiment tracking, model registry")
    print("   • Weights & Biases: Visualization, collaboration")
    print("   • Kubeflow: ML workflow orchestration")
    print("   • Seldon: Model serving platform")
    print("   • Evidently: ML observability")
    print("   • Great Expectations: Data validation")

    print("\n⚡ PERFORMANCE OPTIMIZATION:")
    print("   • Model quantization (reduce size)")
    print("   • Batch inference (throughput)")
    print("   • Caching (reduce latency)")
    print("   • GPU optimization")
    print("   • Async processing")

    print("\n🎯 REAL-WORLD DEPLOYMENT SCENARIOS:")

    print("\n🏥 Healthcare (HIGH RISK):")
    print("   • HIPAA compliance mandatory")
    print("   • Human-in-the-loop for diagnoses")
    print("   • Explainability required")
    print("   • Regular clinical validation")
    print("   • Audit trails for all predictions")

    print("\n💰 Finance (HIGH RISK):")
    print("   • Regulatory compliance (GDPR, FCRA)")
    print("   • Bias testing for loan decisions")
    print("   • Model interpretability")
    print("   • Adverse action explanations")
    print("   • Regular fairness audits")

    print("\n⚖️  Legal (HIGH RISK):")
    print("   • Human review required")
    print("   • Source attribution critical")
    print("   • No hallucinations tolerated")
    print("   • Complete audit trail")
    print("   • Regular accuracy verification")

    print("\n🛒 E-commerce (MEDIUM RISK):")
    print("   • A/B testing for recommendations")
    print("   • Personalization with privacy")
    print("   • Bias monitoring")
    print("   • Performance optimization")
    print("   • User feedback loops")

if __name__ == "__main__":
    demonstrate_production_deployment()
    print("\n" + "=" * 80)
    print("✅ PRODUCTION DEPLOYMENT DEMO COMPLETE!")
    print("=" * 80)
