# MultiBrot
A java renderer for the multibrot

Release and overview here : https://mynameislaurence.com/2015/03/08/fractal/


Full explanation of code and methods used is here: https://mynameislaurence.com/2015/03/03/rendering-complete-multibranch-multibrot-sets/

Please read the above links as they have the correct pictures and formatting.

Features:
  Full multibrot drawing including all possible branches (How does it calculate all the branches?)
  Drawing of all multibrots with a positive rational (p/q) exponent
  Multi threading and realtime image drawing
  Zooming, iteration control and coordinates display
  Different rendering styles (escape time, branch convergence, boundary drawing…) and colours
  Principal branch overlay for multi-valued fractals
  Realtime path drawing and controllable path points and iterations




Settings Explanation:

P = the numerator of the exponent (must be positive)
	
  Q = the denominator of the exponent (must be positive) Try to keep p/q in its simplest form and also the computational cost grows exponentially with q so the lower the better, e.g. if p/q is 5/2 then it takes ~2.5s for 8 iterations but takes ~15s for 12 iterations and ~200s for 16 iterations
  Iterations = the number of iterations to calculate each point to, for q=1 this can be around 100-200 for accurate drawings however when the fractal is multi-valued (q>1) then iterations needs to much lower (2-10) unless you are rendering the escape time and not branch convergence.
  
	Escape radius = the radius of the escape boundary (red circle), if a point iterates outside this circle then it is considered unbounded and not part of the set, 4 is a good value for most fractals apart from when the exponent approaches 1 (e.g. p/q = 5/4)
  
	No paths/constant paths = decides if paths of the point under the mouse should be drawn or not. Each point starts at 0,0 and then starts iterating, following the grey line to each circle, if the circle is green it is the last point and the point is bounded if the circle or line is red then the point has breached the escape radius and so is unbounded. For multi-valued (q>1) fractals, each point iterates to q possible points so there are multiple lines.
 
 Path iterations = the number of iterations to draw the path to
 
 Colour scheme = what to draw:
 
 Branch convergence, how many branches converge, black is divergent
 
 Minimum/Average escape time, how long it takes a point to escape (aka a points ‘velocity’)  
 
 Branch cut edges, render the boundaries between the possible number of branches that converge
 
 Branches and principal/custom branch, render the boundaries and overlay in red the specific branch, 1,1,1,1…1 is the principal branch which is what most software uses to partially render the multibrot, this allows you to clearly see the different branch cuts and how they are related to the real structure of the fractal
 
 X and Y offset = the offset of the center of the image
 
 Set drag area = tick to enable zooming and then click and drag on the image to set the new region to render
 
 Reset view = set the view settings back to normal and un zoomed
  Render width = the width of the part of the fractal to render
  Threads = The number of threads to use when rendering, warning setting it to high can freeze the entire computer, java is greedy
