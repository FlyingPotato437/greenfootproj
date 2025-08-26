# GeographyQuest Explorer - 3 Minute Presentation Script
**Team of 4 People | Total Duration: 3:00 minutes**

---

## **SECTION 1: GAMEPLAY DEMO** *(0:00 - 0:45)*
**Duration: 45 seconds**
**VISUAL: Live gameplay for entire 45 seconds**

**PERSON 1 [0:00-0:22] - While you're playing:**
"Here's GeographyQuest Explorer - an educational game that makes learning U.S. geography addictive! Watch as our explorer moves through levels answering geography questions. Notice how all the orbs look identical - no color hints! You actually have to know the geography to succeed."

**PERSON 2 [0:23-0:45] - Still playing:**  
"See those power-ups and obstacles? This demonstrates real-time object spawning and collision detection. The hint system provides educational clues, and wrong answers teach you the correct information instead of just penalizing you. Progressive difficulty increases as you advance levels."

---

## **SECTION 2: INHERITANCE & POLYMORPHISM CODE** *(0:45 - 1:30)*
**Duration: 45 seconds**
**VISUAL: Show Question.java + subclasses code**

**PERSON 3 explains while showing code:**
"Our game showcases advanced OOP with complex inheritance. Here's our abstract Question class that defines the blueprint for all questions. Notice the abstract methods like generateQuestion() and checkAnswer().

*[Show StateNameQuestion.java, CapitalQuestion.java, RegionQuestion.java]*

These three subclasses inherit from Question but each implements unique behavior. StateNameQuestion asks about state locations, CapitalQuestion tests capitals knowledge, and RegionQuestion covers geographical regions. This is polymorphism in action - same interface, different implementations."

---

## **SECTION 3: GAME LEVEL SYSTEM CODE** *(1:30 - 2:15)*
**Duration: 45 seconds**
**VISUAL: Show GameLevel.java + BeginnerLevel/IntermediateLevel/AdvancedLevel code**

**PERSON 4 explains while showing code:**
"Here's our GameLevel hierarchy demonstrating polymorphic design. The abstract GameLevel class defines common methods like spawnPowerUps() and updateDifficulty().

*[Show BeginnerLevel.java]*
BeginnerLevel has no obstacles, basic scoring, and simple questions.

*[Show IntermediateLevel.java]*
IntermediateLevel adds power-ups and moderate difficulty.

*[Show AdvancedLevel.java]*
AdvancedLevel includes moving obstacles, complex scoring, and the hardest geography questions. Each level overrides methods to create unique gameplay experiences."

---

## **SECTION 4: DATA MANAGEMENT & WRAP-UP** *(2:15 - 3:00)*
**Duration: 45 seconds**
**VISUAL: Show StudentProgress.java + USWorld.java key methods**

**PERSON 1 explains while showing code:**
"Our StudentProgress class demonstrates proper encapsulation with private fields and public getter/setter methods. It tracks learning metrics and maintains high scores using static data management.

*[Show USWorld.java constructor and key methods]*

The main USWorld class orchestrates everything - managing game state, spawning objects dynamically, and coordinating between all our OOP components."

**PERSON 2 concludes [2:45-3:00]:**
"GeographyQuest Explorer combines advanced Java concepts like inheritance, polymorphism, and encapsulation with effective educational design. It's proof that learning games can be both academically rigorous and genuinely fun to play. Thank you!"

---

## **CODE SECTIONS TO DISPLAY:**

### **Section 2 (0:45-1:30) - Inheritance:**
- Question.java (abstract class)
- StateNameQuestion.java 
- CapitalQuestion.java
- RegionQuestion.java

### **Section 3 (1:30-2:15) - Polymorphism:**
- GameLevel.java (abstract class)
- BeginnerLevel.java
- IntermediateLevel.java  
- AdvancedLevel.java

### **Section 4 (2:15-3:00) - Encapsulation:**
- StudentProgress.java (private fields, public methods)
- USWorld.java (main game coordination)

---

## **GAMEPLAY DEMO ACTIONS (0:00-0:45):**
- Start game
- Move explorer with arrow keys
- Show question with hints appearing
- Collect 1-2 orbs (answer questions)
- Hit a power-up if possible
- Show score increasing
- Demonstrate obstacle avoidance

**KEY TALKING POINTS DURING GAMEPLAY:**
- ✅ "All orbs look identical - no cheating!"
- ✅ "Educational hints appear with each question"
- ✅ "Wrong answers teach correct information"
- ✅ "Progressive difficulty system"
- ✅ "Real-time scoring with combo multipliers"

---

**TIMING BREAKDOWN:**
- **0:00-0:45**: Live gameplay with commentary (45s)
- **0:45-1:30**: Inheritance code explanation (45s)  
- **1:30-2:15**: Polymorphism code explanation (45s)
- **2:15-3:00**: Encapsulation + conclusion (45s)