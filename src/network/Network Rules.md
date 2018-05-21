## General Rules for Network

1. The network is a directed graph of nodes *n*<sub>1...*j*</sub> that holds 2 values: a positive real *r* from 1 to 5 inclusive, and a positive real number *v*
2. Each edge between two distinct nodes is weighted with an integral value 1 to 5 inclusive
3. Each node has an edge directed at itself with a weight of 1
4. If *m*<sub>1...*t*</sub> is the set of all adjacent nodes to node *n*, where *r*<sub>*k*</sub> and *v*<sub>*k*</sub> denote the values of *r* and *v* for adjacent *m*<sub>*k*</sub>, and *w*<sub>*k*</sub> denotes the weight of edge (*m*<sub>*k*</sub> , *n*), the value of *r*<sub>*n*</sub> is:  
  
( *f*(*r*<sub>*n*</sub> , *v*<sub>*n*</sub>) + *f*(*r*<sub>1</sub> , *v*<sub>1</sub>) &times; *w*<sub>1</sub> + *f*(*r*<sub>2</sub> , *v*<sub>2</sub>) &times; *w*<sub>2</sub> + ... *f*(*r*<sub>*t*</sub> , *v*<sub>*t*</sub>) &times; *w*<sub>*t*</sub> )/*v*<sub>*n*</sub>  
  
5. If *m*<sub>1...*t*</sub> is the set of all adjacent nodes to node *n*, and *r*<sub>*k*</sub> and *v*<sub>*k*</sub> denote the values of *r* and *v* for adjacent *m*<sub>*k*</sub>, then the value of *v*<sub>*n*</sub> is:  
  
*f*(*r*<sub>*n*</sub> , *v*<sub>*n*</sub>) + *f*(*r*<sub>1</sub> , *v*<sub>1</sub>) + *f*(*r*<sub>2</sub> , *v*<sub>2</sub>) + ... *f*(*r*<sub>*t*</sub> , *v*<sub>*t*</sub>)  

## Rules for *f*(*r*<sub>*n*</sub> , *v*<sub>*n*</sub>)

1. *df*/*dr* > 0
2. *df*/*dv* > 0
3. *d*<sup>2</sup>*f*/*dv<sup>2</sup>* < 0
4. If node *n* has no adjacent nodes, then *v*<sub>*n*</sub> = *f*(*r*<sub>*n*</sub> , *v*<sub>*n*</sub>) = 0 and  *r*<sub>*n*</sub> = 1
5. The only solution to *v*<sub>*n*</sub> = *f*(1 , *v*<sub>*n*</sub>) is *v*<sub>*n*</sub> = 0
6. When *v*<sub>*k*</sub> is defined as *f*(1 , *v*<sub>*k*-1</sub>) for all k > 0, *lim* as *k*->*infinity*(*v*<sub>*k*</sub>) = 0 for all *v*<sub>0</sub>

## Task

1. Find function *f*(*r*<sub>*n*</sub> , *v*<sub>*n*</sub>) such that the values of *r*<sub>*n*<sub>*k*</sub></sub> and *v*<sub>*n*<sub>*k*</sub></sub> converge for all nodes *n*<sub>1...*j*</sub> of network size *j*
2. Optimize *f*(*r*<sub>*n*</sub> , *v*<sub>*n*</sub>) so that the network converges in as few iterations as possible
