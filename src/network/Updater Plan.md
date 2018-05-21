## Task
1. Find a breadth-first algorithm that finds the equilibrium values of *r* and *v* for the entire network, given only the edges between nodes and their weights
2. Find a breadth-first algorithm that updates the values of *r* and *v* so that the only nodes that are updated are those that must be for the value of *r* to stay within .1 of the actual equilibrium value of *r* for each node

## Solution

### Node Updater Rules

1. The minimum potential rating change necessary to trigger an update in a node is denoted as threshhold *t*

2.  If *m* is an outgoing adjacent nodes to node *n*, where *w* denotes the weight of edge *e* = (*n* , *m*),*e*'s the rating change threshhold for *e* to trigger an update in *m* is:

*dw* = (*t* &times; *v*<sub>*m*</sub>)/*f*(*r*<sub>*n*</sub> , *v*<sub>*n*</sub>)

3.  If *m* is an outgoing adjacent nodes to node *n*, where *w* denotes the weight of edge *e* = (*n* , *m*), *n*'s voting power change threshhold for *e* to trigger an update in *m* is:

*df* = (*t* &times; *v*<sub>*m*</sub>)/(*w* - *r*<sub>*m*</sub> &times; *v*<sub>*m*</sub>)

4. If *m* and *n* are non-adjacent noes, and *w* denotes the weight of new edge *e* = (*n* , *m*) from *n* to *m*, the minimum voting power necessary for *e* to cause an update in *m* is:

*f*(*r*<sub>*n*</sub> , *v*<sub>*n*</sub>) = (*t* &times; *v*<sub>*m*</sub>)/(*w* - *r*<sub>*m*</sub> &times; *v*<sub>*m*</sub>)
