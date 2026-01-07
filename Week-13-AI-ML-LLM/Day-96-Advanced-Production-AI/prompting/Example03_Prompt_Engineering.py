"""
Prompt Engineering & Fine-Tuning Techniques
Demonstrates: Advanced prompting strategies for LLMs

Critical Skills for Production AI:
- Zero-shot, few-shot, and chain-of-thought prompting
- Fine-tuning vs prompt engineering
- Production prompt patterns
"""

class PromptLibrary:
    """
    Professional prompt engineering patterns
    Used in production LLM applications
    """

    @staticmethod
    def zero_shot_prompt(task, input_text):
        """
        Zero-shot: No examples provided

        Use when: Task is clear and well-defined
        """
        return f"""
Task: {task}

Input: {input_text}

Output:"""

    @staticmethod
    def few_shot_prompt(task, examples, input_text):
        """
        Few-shot: Provide examples

        Use when: Task needs clarification or specific format
        Critical for: Consistent output format
        """
        examples_text = "\n\n".join([
            f"Input: {ex['input']}\nOutput: {ex['output']}"
            for ex in examples
        ])

        return f"""
Task: {task}

Here are some examples:

{examples_text}

Now, complete this:

Input: {input_text}
Output:"""

    @staticmethod
    def chain_of_thought_prompt(task, input_text):
        """
        Chain-of-Thought (CoT): Ask model to show reasoning

        Use when: Complex reasoning required
        Improves: Accuracy on multi-step problems
        """
        return f"""
Task: {task}

Input: {input_text}

Let's solve this step by step:
1. First, identify the key information
2. Then, apply the relevant rules
3. Finally, arrive at the answer

Step-by-step reasoning:"""

    @staticmethod
    def role_based_prompt(role, task, input_text):
        """
        Role-based: Assign expertise to the model

        Use when: Need domain-specific knowledge
        Examples: "Act as a senior Python developer"
        """
        return f"""
You are {role}.

Task: {task}

Input: {input_text}

Response:"""

    @staticmethod
    def constrained_prompt(task, constraints, input_text):
        """
        Constrained output: Specify exact format

        Critical for: Production systems with parsing
        """
        constraints_text = "\n".join([f"- {c}" for c in constraints])

        return f"""
Task: {task}

Output Constraints:
{constraints_text}

Input: {input_text}

Output (following all constraints):"""

    @staticmethod
    def multi_agent_prompt(agents, task, input_text):
        """
        Multi-agent: Multiple perspectives

        Use when: Need diverse viewpoints or verification
        """
        agents_text = "\n".join([f"- {agent}" for agent in agents])

        return f"""
Task: {task}

Consider this from multiple perspectives:
{agents_text}

Input: {input_text}

Analysis from each perspective:"""

    @staticmethod
    def self_critique_prompt(task, initial_output):
        """
        Self-critique: Model evaluates its own output

        Use when: Need high quality, can afford multiple calls
        """
        return f"""
Original Task: {task}

Your initial answer was:
{initial_output}

Now, critically evaluate your answer:
1. What are potential issues or errors?
2. What could be improved?
3. Provide a revised, better answer.

Self-Critique:"""

    @staticmethod
    def production_qa_prompt(context, question):
        """
        Production Q&A: Enterprise-grade pattern

        Includes: Source attribution, confidence, fallback
        """
        return f"""
You are a helpful AI assistant. Answer questions based on the provided context.

RULES:
1. Only answer based on the given context
2. If the answer is not in the context, say "I don't have enough information"
3. Cite the relevant part of the context
4. Be concise and factual

Context:
{context}

Question: {question}

Answer:"""

def demonstrate_prompt_engineering():
    """
    Demonstrate various prompting techniques
    """
    print("=" * 80)
    print("💡 PROMPT ENGINEERING TECHNIQUES")
    print("=" * 80)

    library = PromptLibrary()

    # Example 1: Zero-shot
    print("\n" + "─" * 80)
    print("1️⃣  ZERO-SHOT PROMPTING")
    print("─" * 80)

    zero_shot = library.zero_shot_prompt(
        task="Classify the sentiment of the following text as positive, negative, or neutral",
        input_text="I absolutely loved this product! It exceeded all my expectations."
    )

    print(zero_shot)
    print("\n📝 Expected Output: Positive")
    print("✅ Best for: Simple, well-defined tasks")

    # Example 2: Few-shot
    print("\n" + "─" * 80)
    print("2️⃣  FEW-SHOT PROMPTING")
    print("─" * 80)

    few_shot = library.few_shot_prompt(
        task="Extract named entities (Person, Organization, Location)",
        examples=[
            {
                'input': "Apple CEO Tim Cook announced new products in California.",
                'output': "Person: Tim Cook | Organization: Apple | Location: California"
            },
            {
                'input': "Microsoft opened a new office in Seattle last week.",
                'output': "Person: None | Organization: Microsoft | Location: Seattle"
            }
        ],
        input_text="Elon Musk's Tesla factory in Texas produced record numbers."
    )

    print(few_shot)
    print("\n📝 Expected: Person: Elon Musk | Organization: Tesla | Location: Texas")
    print("✅ Best for: Specific output formats, complex tasks")

    # Example 3: Chain-of-Thought
    print("\n" + "─" * 80)
    print("3️⃣  CHAIN-OF-THOUGHT (CoT) PROMPTING")
    print("─" * 80)

    cot = library.chain_of_thought_prompt(
        task="Solve this math problem",
        input_text="A store sells apples for $2 each and oranges for $3 each. "
                   "If you buy 5 apples and 3 oranges, how much do you spend?"
    )

    print(cot)
    print("\n📝 Expected reasoning:")
    print("   Step 1: Cost of apples = 5 × $2 = $10")
    print("   Step 2: Cost of oranges = 3 × $3 = $9")
    print("   Step 3: Total = $10 + $9 = $19")
    print("✅ Best for: Math, logic, complex reasoning")

    # Example 4: Role-based
    print("\n" + "─" * 80)
    print("4️⃣  ROLE-BASED PROMPTING")
    print("─" * 80)

    role_based = library.role_based_prompt(
        role="a senior software architect with 15 years of experience in distributed systems",
        task="Review this architecture and suggest improvements",
        input_text="We're planning to use a monolithic database for a social media app with 1M users."
    )

    print(role_based)
    print("\n✅ Best for: Domain expertise, technical tasks")

    # Example 5: Constrained
    print("\n" + "─" * 80)
    print("5️⃣  CONSTRAINED OUTPUT PROMPTING")
    print("─" * 80)

    constrained = library.constrained_prompt(
        task="Summarize the following article",
        constraints=[
            "Use exactly 3 bullet points",
            "Each bullet must be under 20 words",
            "Start each bullet with an action verb",
            "Output only the bullets, no additional text"
        ],
        input_text="Article about AI safety, regulations, and future implications..."
    )

    print(constrained)
    print("\n✅ Best for: Production systems with parsing, API integration")

    # Example 6: Multi-agent
    print("\n" + "─" * 80)
    print("6️⃣  MULTI-AGENT PROMPTING")
    print("─" * 80)

    multi_agent = library.multi_agent_prompt(
        agents=[
            "Security Expert: Focus on security vulnerabilities",
            "UX Designer: Focus on user experience",
            "Performance Engineer: Focus on speed and scalability"
        ],
        task="Evaluate this feature design",
        input_text="Auto-login feature that stores user credentials in browser localStorage"
    )

    print(multi_agent)
    print("\n✅ Best for: Comprehensive analysis, risk assessment")

    # Example 7: Self-critique
    print("\n" + "─" * 80)
    print("7️⃣  SELF-CRITIQUE PROMPTING")
    print("─" * 80)

    self_critique = library.self_critique_prompt(
        task="Write a product description for wireless headphones",
        initial_output="These headphones are great. They have good sound and battery life."
    )

    print(self_critique)
    print("\n✅ Best for: High-quality outputs, iterative refinement")

    # Example 8: Production Q&A
    print("\n" + "─" * 80)
    print("8️⃣  PRODUCTION Q&A PROMPTING")
    print("─" * 80)

    production_qa = library.production_qa_prompt(
        context="""
        Company Policy: Employees receive 15 days of paid vacation per year.
        Vacation days can be carried over to the next year, up to a maximum of 5 days.
        Employees must submit vacation requests at least 2 weeks in advance.
        """,
        question="How many vacation days can I carry over?"
    )

    print(production_qa)
    print("\n✅ Best for: Enterprise applications, compliance-critical systems")

    # Prompt Engineering Best Practices
    print("\n" + "=" * 80)
    print("🎯 PROMPT ENGINEERING BEST PRACTICES")
    print("=" * 80)

    print("""
┌──────────────────────────────────────────────────────────────────────┐
│                     PROMPTING STRATEGIES                             │
├──────────────────────────────────────────────────────────────────────┤
│                                                                       │
│  1. BE SPECIFIC & CLEAR                                              │
│     ❌ "Tell me about Python"                                        │
│     ✅ "List 5 key differences between Python 2 and Python 3"       │
│                                                                       │
│  2. PROVIDE CONTEXT                                                   │
│     ❌ "How do I fix this?"                                          │
│     ✅ "I'm getting a TypeError in my Flask app when parsing JSON.  │
│         Here's the code: [code]. How do I fix it?"                   │
│                                                                       │
│  3. SPECIFY OUTPUT FORMAT                                             │
│     ❌ "Give me some ideas"                                          │
│     ✅ "Provide 3 ideas in bullet points, each under 15 words"      │
│                                                                       │
│  4. USE EXAMPLES (Few-shot)                                           │
│     ❌ "Parse this address"                                          │
│     ✅ "Parse address into JSON. Example:                            │
│         Input: '123 Main St, NYC' → {'street':'123 Main St',        │
│         'city':'NYC'}"                                               │
│                                                                       │
│  5. BREAK DOWN COMPLEX TASKS                                          │
│     ❌ "Build a web app"                                             │
│     ✅ "Step 1: Design database schema. Step 2: Create API          │
│         endpoints. Step 3: Build frontend."                          │
│                                                                       │
│  6. ADD CONSTRAINTS                                                   │
│     ❌ "Summarize this"                                              │
│     ✅ "Summarize in exactly 50 words, suitable for executives"     │
│                                                                       │
│  7. REQUEST REASONING                                                 │
│     ❌ "Is this code correct?"                                       │
│     ✅ "Is this code correct? Explain your reasoning step-by-step"  │
│                                                                       │
│  8. SPECIFY EXPERTISE LEVEL                                           │
│     ❌ "Explain quantum computing"                                   │
│     ✅ "Explain quantum computing to a 10-year-old" OR               │
│        "Explain quantum computing assuming PhD-level physics"        │
│                                                                       │
└──────────────────────────────────────────────────────────────────────┘
    """)

    print("\n" + "=" * 80)
    print("⚖️  PROMPT ENGINEERING VS FINE-TUNING")
    print("=" * 80)

    print("""
╔═══════════════════════════════════════════════════════════════════════╗
║         WHEN TO USE PROMPT ENGINEERING VS FINE-TUNING                 ║
╠═══════════════════════════════════════════════════════════════════════╣
║                                                                        ║
║  PROMPT ENGINEERING (Start here!)                                    ║
║  ───────────────────────────                                         ║
║  ✅ Faster iteration (seconds vs days)                               ║
║  ✅ No training data required                                        ║
║  ✅ No compute costs for training                                    ║
║  ✅ Easy to update and modify                                        ║
║  ✅ Good for general tasks                                           ║
║                                                                        ║
║  Best for:                                                            ║
║  • New projects (MVP, prototyping)                                   ║
║  • Changing requirements                                             ║
║  • Limited training data                                             ║
║  • General-purpose tasks                                             ║
║                                                                        ║
║  ─────────────────────────────────────────────────────────────────   ║
║                                                                        ║
║  FINE-TUNING (When prompting isn't enough)                           ║
║  ───────────────────────────────────────                             ║
║  ✅ Better performance on specific tasks                             ║
║  ✅ Smaller prompts (lower latency/cost)                             ║
║  ✅ Consistent behavior                                              ║
║  ✅ Can learn new formats/styles                                     ║
║                                                                        ║
║  Best for:                                                            ║
║  • High-volume production use                                        ║
║  • Domain-specific vocabulary                                        ║
║  • Consistent output format critical                                 ║
║  • Have large training dataset (1000+ examples)                      ║
║                                                                        ║
║  Costs:                                                               ║
║  • Training: $100-$1000+ per model                                   ║
║  • Time: Hours to days                                               ║
║  • Data labeling: $$$                                                ║
║                                                                        ║
╚═══════════════════════════════════════════════════════════════════════╝
    """)

    print("\n" + "=" * 80)
    print("🏭 PRODUCTION PROMPTING PATTERNS")
    print("=" * 80)

    print("""
1️⃣  CLASSIFICATION PATTERN
   ```
   Classify the following [INPUT_TYPE] into one of these categories:
   [CATEGORY_1], [CATEGORY_2], [CATEGORY_3]

   [INPUT_TYPE]: [INPUT_TEXT]

   Category:
   Confidence (0-100):
   Reasoning:
   ```

2️⃣  EXTRACTION PATTERN
   ```
   Extract the following information from the text:
   - Field1: [description]
   - Field2: [description]

   Text: [INPUT_TEXT]

   Output as JSON:
   ```

3️⃣  TRANSFORMATION PATTERN
   ```
   Convert the following [SOURCE_FORMAT] to [TARGET_FORMAT]:

   Input ([SOURCE_FORMAT]): [INPUT_TEXT]

   Output ([TARGET_FORMAT]):
   ```

4️⃣  GENERATION PATTERN
   ```
   Generate a [OUTPUT_TYPE] with the following characteristics:
   - Characteristic 1: [value]
   - Characteristic 2: [value]
   - Length: [value]
   - Tone: [value]

   Topic: [INPUT_TEXT]

   Generated [OUTPUT_TYPE]:
   ```

5️⃣  EVALUATION PATTERN
   ```
   Evaluate the following [ITEM_TYPE] based on these criteria:
   1. Criterion 1 (weight: X%)
   2. Criterion 2 (weight: Y%)

   [ITEM_TYPE]: [INPUT_TEXT]

   Scores:
   - Criterion 1: [0-100]
   - Criterion 2: [0-100]
   Overall: [0-100]
   Explanation:
   ```

6️⃣  COMPARISON PATTERN
   ```
   Compare the following two [ITEM_TYPE]:

   Option A: [TEXT_A]
   Option B: [TEXT_B]

   Comparison Criteria:
   - [Criterion 1]
   - [Criterion 2]

   Analysis:
   Recommendation:
   ```
    """)

    print("\n💡 Advanced Prompting Techniques:")

    print("\n🔹 Temperature & Top-P:")
    print("   • Temperature = 0: Deterministic, factual (Q&A)")
    print("   • Temperature = 0.7: Balanced (most use cases)")
    print("   • Temperature = 1.0: Creative (content generation)")
    print("   • Top-P: Alternative to temperature for diversity")

    print("\n🔹 System Prompts (ChatGPT API):")
    print("   • Sets global behavior")
    print("   • Example: 'You are a helpful assistant that provides concise answers'")
    print("   • Persists across conversation")

    print("\n🔹 Prompt Chaining:")
    print("   • Break complex task into steps")
    print("   • Output of prompt 1 → input to prompt 2")
    print("   • Better than one giant prompt")

    print("\n🔹 Self-Consistency:")
    print("   • Generate multiple outputs")
    print("   • Take majority vote")
    print("   • Improves accuracy on reasoning tasks")

    print("\n🏭 Production Monitoring:")
    print("   📊 Track prompt performance")
    print("   📊 Monitor output quality")
    print("   📊 A/B test different prompts")
    print("   📊 Collect user feedback")
    print("   📊 Version control prompts (Git)")
    print("   📊 Cost monitoring (tokens used)")

if __name__ == "__main__":
    demonstrate_prompt_engineering()
    print("\n" + "=" * 80)
    print("✅ PROMPT ENGINEERING DEMO COMPLETE!")
    print("=" * 80)
