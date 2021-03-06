See the project page at http://pl.cs.colorado.edu/projects/thresher/ for more information on the Thresher project.

Installation
============
(1) You can download all dependencies by running:

     make install-deps

Go to step (2) if this works. Otherwise, go to step (3).

(2) Download WALA (git clone https://github.com/wala/WALA.git) and move it to /lib.

(3) Build dependencies by running:

    make deps

If this works, all dependencies have been installed and built and Thresher should build without a hitch.

(5) Once all dependencies have been built successfully, return to the top-level Thresher directory and run
  
    make
    make tests

to build Thresher and its regression tests. 

Running Thresher
----------------

Right now, Thresher can do four things:

(1) Check for Android Activity leaks (see our PLDI '13 paper). Run:

     ./thresher.sh -android_leak -app <path_to_bin_dir_for_your_app>

(2) Check downcast safety. Run: 
    
    ./thresher.sh -check_casts -app <path_to_bin_dir> <path_to_bin_dir_for_your_app> -main_class <class_with_entrypoint_method> -main_method <entrypoint_method>

(3) Check assertions from edu.colorado.external.Assertions.java. Run:

    ./thresher.sh -check_assertions -app <path_to_bin_dir_for_your_app> -main_class <class_with_entrypoint_method> -main_method <entrypoint_method>

(4) Check annotations from edu.colorado.external.Annotations.java. Run:

    ./thresher.sh -check_annotations -app <path_to_bin_dir_for_your_app> -main_class <class_with_entrypoint_method> -main_method <entrypoint_method>
   
When Thresher says that a leak does not exits/cast cannot fail/assertion cannot fail/annotation holds, this is a "soundy" guarantee (modulo reflection, exceptions, and bugs in the implementation). When Thresher cannot prove any of the above, the program may contain either a real bug or a false alarm.

Thresher works by using a coarse up-front points-to analysis to focus a precise symbolic analysis on the alarms reported by the points-to analysis. See our PLDI '13 paper for more details.

