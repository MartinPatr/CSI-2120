#lang racket
(define (numNotDiv a b d)
  (cond ((> a b) 0)
        ((= (modulo a d) 0) (numNotDiv (+ a 1) b d))
        (else(+ (numNotDiv(+ a 1) b d) 1)))
)

(numNotDiv 6 12 4) ; 5
(numNotDiv 1 10 3) ; 7

(define (missing L x)
  (cond
    ((<= x 0)
     '())
    ((not(member x L))
     (append (missing L (- x 1)) (list x)))
    (else
     (missing L (- x 1))))
  )


(missing '(2 4 6 1) 5) ; '(3 5)
(missing '(1 2 3 1 5 6 5 4 2) 7); '(7)


(define (coin-change L n)
  (if (null? L)
      '()
      (let ((fir (car L)))
        (append  (list (quotient n fir)) (coin-change (cdr L) (- n (* (quotient n fir) fir)))))
      )
  
  )


(coin-change '(100 50 20 10 5) 345) ; ‘(3 0 2 0 1)
(coin-change '(16 3 1) 26) ; ‘(1 3 1)