// lamp3 agent

/* Initial beliefs */


/* Initial goal */

!start.

/* Plans */

+!start: true<-
	!turnoff.

+!turnoff : true <- 
	turnoff3;
	.wait(25000);
	!green.
	
+!green : true <- 
	.wait(10000);
	switch3;
	  !red.

+!red : true <- 
	.wait(10000);
	switch3;
	  !green.
	  
