This is a review of the 'Pure Engine' subproject (from now on, I will usually refer to it as 'project' without being confused with this repository contents as a whole). 
A conceptual overview will be given, and its documentation structure will be explained.

# 'Pure' engine

The idea behind the project itself is to create a match engine 'from scratch', without any specific objective or approach beforehand. 
What this actually means will be clarified in the rest of this documentation, and as the project itself takes shape.

The mode of development in this project is to start with the simplest possible football match 'simulation engine', and evolve it with certain of its aspects being developed. 
While I have a basic idea and outline of what these 'aspects' are or may be, I won't elaborate on them right now, as there is not a coherent terminology or categorization of them at the moment. 
Rather, these aspects will be 'revealed' and specified as this particular project is being developed, and in the same way as the project will be developed: incrementally.

I am calling the project's increments 'levels'. Each level will be a 'full' match engine simulation, in the sense that there will be some inputs and outputs, 
that will produce the impression of a football match being simulated. With each level, one (or more, but usually should be just one) 'aspect' of the match engine will be elaborated. 
Just a simple example of a potential 'elaboration': suppose our simple match engine simulates only the final score. 
The next level to this engine could be to make it calculate the actual shots taken by each team and then the goals scored. 
This is just a simple idea of how the increments/levels will evolve, and the specifics of that potential iteration don't matter at the moment 
(i.e. we may 'increment' it in a different way when we reach the implementation of that level).

This document will define each match engine 'level', based on certain aspects such as inputs, outputs, mechanism, etc. 
These aspects will be more rigorously defined as the project itself evolves, and more levels are implemented. With the definition and documentation of the sequence of levels, each iteration of the match engine, 
and the project as a whole, will be specified as succintly as possible. In addition, as each level will be autonomous, it will be able to be reused depending on the sophistication needs of a potential 'host project'.

## Why this (sub-)project?

As I hinted above, implementing and documenting each iteration as a 'level' has the advantages of clarity and reusability. What the evolution of the project will hopefully do is identify these aspects of a match engine that
make up its actual components and their variations. The more sophisticated a level is, the more it deserves to be its own project, and this is currently what's being done with the rest of the sub-projects 
(currently: MPN and 'Abstract Model'), which are just 'levels' in the present sub-project's terms, or 'approaches' in general. 
Exploring the different approaches (in an incremental manner, thus the term 'levels') to a match engine, we will be more aware of what we try to achieve with each approach/sub-project/increment/level along with 
its limitations, while we will have a rigorous roadmap towards a unified and complete ('ideal') approach, as a demonstration of a (not necessarily always straight) path from simplicity to accuracy.
