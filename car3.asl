// car3 agent

/* Initial beliefs */


/* Initial goal */

!move. 

/* Plans */

+!move : ninocoming<-
	wait(car3).

+!move : true <-
	.wait(500)
	move(car3);
	!move.

+ninoarrived:true<-
	!move.
