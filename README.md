# Optional task 1

## Task ##

Given a circular linked list, implement an algorithm that returns node at the beginning of the loop.\
**DEFINITION**\
A circular linked list is a list in which a node's next pointer points to an earlier node, so as to make a loop.\
**EXAMPLE**\
input: A -> B -> C -> D -> E -> C (the same C as earlier)\
output: C

## Algorithm explanation ##

The fast iterator is twice as fast as the slow one. Assume that:
* $m$ - length of list outside the loop
* $n$ - length of list loop
* $k$ - distance from the beginning of the loop to the node where the iterators overlapped (if loop exists)
* $x$, $y$ - number of cycles passed by the fast and slow iterator respectively

If we have a loop, these iterators will meet in a certain node inside the loop. Then we can explain the distances traveled as follows:

$$m + nx + k = 2(m + ny + k)$$

$$m + k = n(x - 2y)$$

Here $(x-2y)$ is a period, so in terms of distance: 

$$m = n-k$$

It means if the slow iterator starts from the head and another one continues through the loop with the same step, they will meet in the beginning of the loop.

![image](https://user-images.githubusercontent.com/99670019/209309613-a3847a88-70a2-4246-b584-44ee8343f4b3.png)
