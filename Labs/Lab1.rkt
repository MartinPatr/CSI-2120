#lang racket

; Question 1
(- ( + (* 13 22) 7) ( * ( / 51 64) (- 19 (/ 45 ( + 32 11)))))

;Question 2
(define PI 3.1415)
(define PI/2 ( / 3.1415 2))
(define PI/4 (/ 3.1415 4))

(define (leq1 x)
(+ (expt (sin x) 2) (expt (cos x) 2)))

(define (leq2 x)
(sin ( * x 2)))

(define (req2 x)
(* 2 ( * ( sin x) ( cos x))))

(define (leq3 x)
 (cos (* 2 x)))

(define (req3 x )
  (- (expt (cos x) 2) (expt (sin x ) 2)))

;Question 3
(define (fact n)
(if (= n 0)
    1
    (* n (fact (- n 1))))   
)


(define (power x y)
  (if (= y 1)
      x
      (* x (power x (- y 1))))
)


