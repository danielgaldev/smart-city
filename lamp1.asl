// lamp1 agent

/* Initial beliefs */


/* Initial goal */

!start.

/* Plans */

+!start: true<-
	!turnoff.

+!turnoff : true <- 
	turnoff1;
	.wait(25000);
	!green.
	
+!green : true <- 
	.wait(10000);
	switch1;
	  !red.

+!red : true <- 
	.wait(10000);
	switch1;
	  !green.
	  
