source: https://blog.pramp.com/how-to-succeed-in-a-system-design-interview-27b35de0df26

# How to Succeed in a System Design Interview

System design questions can often be as general as “How would you design Product X?”

“Oh, you mean what would be the best way to design a product that has had hundreds of software engineers working on it for a decade? Sure, let me answer that in 45 minutes or less!”
Real-world system design is challenging and complex, especially for large systems with varying requirements and constraints. An unbelievable amount of complexity can also be found beneath something as simple as visiting Google in your browser. While the end user is blind to much of this complexity, as a system designer, you must face it head-on.

Given the complexity of system design, it’s impossible to cover all the various topics and tradeoffs in under an hour. Moreover, unlike data structures and algorithms questions, there isn’t just one optimal solution, and different interviewers can conduct vastly different interviews based on the same question by focusing on different aspects of the system.

Think of a system design interview as a brainstorming session, driven by open-ended questions, in which you’ll be expected to competently discuss a complex system. Consider it an opportunity to work with your interviewer, just as two team members would, to solve a real problem related to the company’s goals.

The good news is that you don’t need to know everything! Your final design is not as important as the thought process behind your design choices. After all, this reflects the experience of actually working at a company. Engineers have a tremendous amount of freedom; we aren’t asked to implement fully-specced features, but rather to take ownership of open-ended problems and come up with the best solutions.

To develop the skills needed to ace a system design interview, you need to familiarize yourself with the sort of topics that a question might involve and how to approach them. You’ll also need to be able to methodically explore different directions, ask the right questions, and understand best practices and common pitfalls of modern software systems.

Actual experience with a wide range of tools and systems is an advantage, but being able to identify a need and suggest a common solution for it would get you a long way, even if you’ve never used it yourself. For instance, recognizing that you need a load balancer and naming NGINX as a popular choice is more important than being able to drill down into the details of how to configure one product or another to perform as a load balancer.

In these cases, the magic ingredient is honesty — you should always be confident in saying, “While I’ve never used technology X, I know it’s a common solution to problem Y.” The combination of honesty, confidence, and a willingness to learn will leave a much better impression on your interviewer than throwing around incoherent tidbits about a product you’ve never really used in production.

## A Step-by-Step Approach to Acing your System Design Interview
While every system design interview is different, there are some common steps you should cover, even if the conversation might not be as sequential as your ideal thought process.

The system design interview is an open-ended conversation, which you’ll be expected to lead. Try using the following steps to guide your discussion:

### Step 1 — Understand the Goals
Clarifying ambiguities early in the interview is critical. Candidates who spend time clearly defining the end goals of the system have a better chance of success than those who don’t. For that reason, make sure you understand the basic requirements and ask clarification questions. Start with the most basic assumptions:

* What is the goal of the system?
* Who are the users of the system? What do they need it for? How are they going to use it?
* What are the inputs and outputs of the system?

Even if you’re asked about a well-known product, you should still share your assumptions about it with your interviewer. You may find that you and your interviewer don’t have the same assumptions about products like Twitter, Facebook, or Reddit. In addition to helping you focus, it also demonstrates product sensibility and good teamwork.

### Step 2 — Establish the Scope
Now that you understand the system, try to describe the feature set that you’ll be talking about. Try to define all the features that you think of by their importance to the user. You don’t have to get it right on your first attempt, but make sure that you and your interviewer agree.

Ask clarifying questions, such as:

* Do we want to discuss the end-to-end experience or just the API?
* What clients do we want to support (mobile, web, etc)?
* Do we require authentication? Analytics? Integrating with existing systems?

Take a few minutes to discuss this with your interviewer, and write it down.

### Step 3 — Design for the Right Scale
The same feature set requires a very different approach for different scales. It’s important to determine the scale so that you know whether your data can fit on one machine or whether you need to scale the reads. You might ask:

* What is the expected read-to-write ratio?
* How many concurrent requests should we expect?
* What’s the average expected response time?
* What’s the limit of the data we allow users to provide?

Different answers require very different designs, so getting the scale right is key to success.

### Step 4 — Start High-Level, then Drill-Down
Start with covering the end-to-end process, based on the goals you’ve established. This might include detailing different clients, APIs, backend services, offline processes, network architecture, data stores, and how they all come together to meet the requirements.

This is also a good point to identify the system’s entry-points, such as:

- User interaction
- External API calls
- Offline processes

This will allow the conversation to drill down into potential performance bottlenecks, and decisions about the separation of responsibilities. Whichever approach you choose to start with, remember to always start simple, and iterate.

### Step 5 — Data Structures and Algorithms (DS&A)
Wait, this again? Yes! Turns out, this is actually important in designing software systems.

URL shortener? Makes me think of a hashing function. Oh, you need it to scale? Sharding might help. Concurrency? Redundancy? Generating keys becomes even more complicated. Same goes for designing an analytics system, a news feed, or a Q&A forum, each with its own set of common DS&A.

Don’t forget to account for your scaling requirements, where analyzing runtime and memory complexity really becomes handy.

### Step 6 — Tradeoffs
Almost every decision will involve a trade off. Being able to describe them in real time, as you’re suggesting solutions, shows that you understand that complex systems often require compromises and allow you to demonstrate your knowledge regarding the pros and cons of different approaches. Since there’s no one correct answer, having this discussion will give your interviewer the impression that you’re practical and will use the right tool for the job.

A few common examples might include:

* What type of database would you use and why?
* What caching solutions are out there? Which would you choose and why?
* What frameworks can we use as infrastructure in your ecosystem of choice?
