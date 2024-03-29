These are notes on the evolution of various notations which were developed for the input 'dataset' to the simulator.

In general, the common elements in all these notations are used to describe transitions, whose sequence defines the 'match flow'.
These common elements are: actions, phases, pitch positions, and tactical positions.
Each notation captures a subset of these elements with different syntax, depending on the approach to be taken at a specific time.
As the simulator and related ideas evolved, different notations were tried.

* Actions: player actions, such as 'pass', 'shoot', etc.
* Phases: match phases. Although developed independently, and with different terminology and categorization, see also: https://www.youtube.com/watch?v=1W4bRAKfMeM
* Pitch positions: Should be kind of obvious (most of them). Each value corresponds to pitch area coordinates. 
As the notations evolved, their terminology has become clearer. However, I owe you a visual representation with the pitch
as grid with the corresponding labels.
* Tactical position: The player's tactical position according to the team's formation. Should be kind of obvious, but proper
documentation is due, as well.

# Current status

At this stage, there are two approaches I am exploring: a data-oriented one and one based on an 'average pitch-control' model.

The general idea behind the simulator is to be a processor of input data with minimal logic ('interventions') on its 
own. One example of this is the handling of penalty kicks, and scoring in general. Using the data as they are with no 
interventions, and using the current data set, only Liverpool would score goals and 50% of that would be with penalties.
One intervention would be to award penalties based on their probability to be awarded in all matches and not in that
match alone. Another intervention would be to take into account the xG of each goal attempt and calculate whether it is
successful or not. These as I said, would be interventions, which would account for the low amount of data, but also
it's about being proactive over the input data. Ideally, with enough data these interventions would be 
redundant.

Using a data oriented approach removes the burden of writing complicated logic, and exploring all the possible scenarios
in our code. While recognizing patterns in a football match would be easier than, say, categorizing images, a 'perfect'
simulation would still be difficult to write from scratch. And in any case you need the data to have plausible and
realistic results. The approach is similar to deep learning, where the model is trained on input data, with its actual
'logic' (as in actual programming logic) being agnostic to the problem at hand. In this project, the idea is to make
the data work for us, and allowing only for obvious interventions, which would compensate for the inadequacies of using
a strict data-oriented approach. Not that interventions are bad in themselves. If we could program all this from scratch 
without needing to be backed by data our life would be easier. Except that we can't (or we can't 'easily').

Based on the above discussion, we have two complementary approaches which would need to be balanced: data processing
and interventions. The data oriented approach has been the dominant one so far. Even with a small sample of data and 
suitable interventions it can yield plausible and varying results, as demonstrated in the 'legacy' openfootie project: 
https://github.com/atas76/openfootie. However, there are some additional disadvantages in the data oriented approach, 
into which I won't go into detail at this point. Another approach I am currently trying is that of minimal usage of data 
as a starting point (the two approaches will converge eventually as we would still need to be based on data with minimal 
or zero interventions). The latest approach is also better for taking tactics into account, but creating an 'average 
pitch control' model for each formation as a starting point. I will also not go into detail about this at this point, as it would 
be out of scope, and it's a work in progress anyway.

This has been a long introduction to the actual topic of what data formats are currently being used:

* MPN

The match phase notation feeds state transitions into the program, in the form of transitions between match phases.
This will be augmented with more accurate data, as the match engine evolves based on this approach.

* Attacking Profiles

This is for the 'Abstract Model' approach. As this approach builds on an average pitch control model (which can be just 
calculated), the additional data we need would be the xG and the probability of goal attempts, and this kind of data
is stored in Attacking Profiles. If this is not clear at this point, don't worry. I will write documentation on this,
once it is completed. However, the contents of the attacking profiles file should be fairly self-explanatory.

**UPDATE**: there is an even better and more inclusive approach to this (with evaluating more kinds of actions), however
I will reveal it once that part of the project is finalized. More importantly, I came across the data model I need for this, so
attacking profiles are redundant at this point, but the idea is similar, although much more generic.

# Event data

The data created and used in this project can be classified as 'event data', but with a twist: while in actual event 
data there are coordinates which show the exact player position on the pitch, I am taking a more simplistic approach 
where I designate pitch areas in place of actual coordinates. For the needs of this simulation, I think this is a good 
enough and even suitable approach as the simulation itself does not have accuracy as its priority at this stage. In any 
of its forms and possible incarnations throughout its development, we would only need the teams' relative strengths in 
each area on the pitch at each moment in time, and designating the suitable pitch areas in the form of a custom 
'coordinate system' is more than enough for that.

A beneficial side effect of this approach is human-readability. While event data from various providers are accurate
and used in producing analytics and even reconstructing the game itself visually, they are not very
intuitive for being read by a human, especially regarding their usage of coordinates. Not that they cannot be 
transformed easily to be human-readable, and also maybe there is no need for that, as what may be more important 
are the analytics and visual representations produced. In any case, human-readability being an asset or not, 
the notations in this project are, I think, intuitive enough, if someone gets familiarized, so they can follow the 
progression of the match by reading their text as a script. This would allow for manual scanning of the event - or even 
more importantly - tracking data, that could quickly identify patterns in the data that couldn't be identified in 
advance easily from traditional event data or without watching the match.

# Notations

Presenting the following notations was the reason I started writing this document, however since the following descriptions
summarize the project's history, it was also useful starting this document with a more generic project review, as an introduction, which
eventually became the main part of this document, while the present section serves more as an appendix.

So, these are the notations you will find in datasets of this project. The only one that matters currently is the 'mpn' one,
but the other ones are also useful for getting an idea of this project's evolution, while some of the ideas and concepts
behind them may or will re-surface in the future.

## fgn (11/19 - 04/20)

Transitions between pitch positions with actions. 

Elements: actions, pitch positions

## fgs - Game Simple notation (05/20 - 06/20)

More like a simplified successor to FGN, in terms of syntax.

Elements: actions, pitch positions

## ftn - Flow Tracking Notation (07/20 - 05/22)

Let's define 'flow' as a sequence of transitions between pitch positions or phases.
This format is structured as records of pitch positions transitions with corresponding actions within 'phase blocks', which are supposed to add context.

Elements: actions, phases, pitch positions

## etn - Easy tracking notation (04/21 - 05/23)

Started as recording transitions between pitch positions with corresponding actions and within 'match phase blocks', and as a simplified version of FTN.
The idea during the last stages was to capture transitions between phases.
It evolved to be reused between different project approaches.

* 0.0.1: Meant for a 'minimal' implementation

* 0.0.2: 'Simplification'

* 0.0.3: Introduce coordinates for within penalty area

* 0.1: Simplify by including only match phase records and omitting transitions

* 1.0: Not sure what I was trying to do with this version; seems more like the 0.1 version with extra 'features'

* 2.0: Add pitch positions

Elements:
    
* 0.0.x: actions, pitch positions, phases
* 0.1: phases
* 1.0: phases
* 2.0: phases, pitch positions

## ttn - Tactical Tracking Notation (11/21 - 05/23)

The first attempt to include tactical positions in state transitions.
Since we need to use tactics as input to the simulator, this is an important element, but also the most complex.

Elements: actions, pitch positions, phases, tactical positions

## fmx - Flow matrix 2.0 (01/23 - 05/23)

Map (initial phase, pitch position) pair to outcome (phase, pitch position) pairs.

It will be obsoleted by mappings generated automatically in the MPN subproject.

## cmx - Causal matrix (02/23 - 05/23)

Unnecessarily complicated. When hardcoding seemed more efficient than automating...

## pnw - Passing Network Data (03/23 - 04/23)

Maps transitions between tactical positions, where actions are passes. Meant for defining passing networks as input data to the simulator.
Could be a useful approach, but probably will be integrated in more comprehensive approaches.

Elements: actions (passes), phases, tactical positions

## mpn - Match Phase Notation (11/23 - )

The current format used. This is basically the same idea as ETN, recording match phase transitions explicitly and quite more intuitively.

The 2.0 format is enhanced with pitch positions (important!).

Also, the mappings between initial phases and their transition outcomes are generated automatically within the MPN project.

Elements: phases, pitch positions 
