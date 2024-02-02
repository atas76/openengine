These are notes on the evolution of various notations which were developed for the input 'dataset' to the simulator.

In general, the common elements in all these notations are used to describe transitions, whose sequence defines the 'match flow'.
These common elements are: actions, phases, pitch positions, tactical positions.
Each notation captures a subset of these elements with different syntax, depending on the approach to be taken at a specific time.
As the simulator and related ideas evolved, different notations were tried.

* Actions: player actions, such as pass, shoot, etc.
* Phases: match phases. Although developed independently, and with different terminology and categorization, see also: https://www.youtube.com/watch?v=1W4bRAKfMeM
* Pitch positions: Should be kind of obvious (most of them). Each value corresponds to pitch area coordinates. As the notations evolved, their terminology has become clearer.
* Tactical position: The player's tactical position according to the team's formation

## TODO

* Map notation elements to each notation
* Write a bit about current state of development regarding the notations currently used
* Write a bit about the rationale behind these notations, in relation to existing event data
* Editing

# fgn (11/19 - 04/20)

Transitions between pitch positions with actions. ETN initially was supposed to be a simpler version of this.

# fgs - Game Simple notation (05/20 - 06/20)

More like a simplified successor to FGN.

# ftn - Flow Tracking Notation (07/20 - 05/22)

Let's define 'flow' a sequence of transitions between pitch positions or phases.
This format is structured as records of pitch positions transitions with corresponding actions within 'phase blocks', which are supposed to add context.

# etn - Easy tracking notation mapping (04/21 - 05/23)

Started as recording transitions between pitch positions with corresponding actions and within 'match phase blocks', and as a simplified version of FTN.
The idea during last stages was to capture transitions between phases.
It evolved to be reused between different project approaches.

* 0.0.1: Meant for a 'minimal' implementation

* 0.0.2: 'Simplification'

* 0.0.3: Introduce coordinates for within penalty area

* 0.1: Simplify by including only match phase records and omitting transitions

* 1.0: Not sure what I was trying to do with this version; was not finalized

* 2.0: Add pitch positions

# ttn - Tactical Tracking Notation (11/21 - 05/23)

The first attempt to include tactical positions in transitions.
Since we need to use tactics as input to the simulator, this is an important element, but also the most complex.

# fmx - Flow matrix 2.0 (01/23 - 05/23)

Map (initial phase, pitch position) pair to outcome (phase, pitch position) pairs.

It will be obsoleted by mappings generated automatically in the MPN subproject.

# cmx - Causal matrix (02/23 - 05/23)

Unnecessarily complicated. When hardcoding seemed more efficient than automating...

# pnw - Passing Network Data (03/23 - 04/23)

Maps transitions between tactical positions, where actions are passes. Meant for defining passing networks as input data to the simulator.
Could be a useful approach, but probably will be integrated in more comprehensive approaches.

# mpn - Match Phase Notation (11/23 - )

The current format used. This is basically the same idea as ETN, recording match phase transitions explicitly and quite more intuitively.

The 2.0 format is enhanced with pitch positions (important!).

Also, the mappings between initial phase and their transition outcomes are generated automatically within the MPN project.
