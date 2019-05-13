// car4 agent

/* Initial beliefs */


/* Initial goal */

!move. 

/* Plans */

+!move : ninocoming<-
	wait(car4).

+!move : true <-
	move(car4);
	!move.

+ninoarrived:true<-
	!move.
