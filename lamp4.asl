// lamp4 agent

/* Initial beliefs */


/* Initial goal */

!start.

/* Plans */

+!start: true<-
	!turnoff.

+!turnoff : true <- 
	turnoff4;
	.wait(25000);
	!green.
	
+!green : true <- 
	.wait(10000);
	switch4;
	  !red.

+!red : true <- 
	.wait(10000);
	switch4;
	  !green.
	  
