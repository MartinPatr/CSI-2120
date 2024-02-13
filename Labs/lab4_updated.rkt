#lang racket
(define (rotate vec m)
  (let ((len (vector-length vec)))
    (let ((rotated (make-vector len)))
      (do ((i 0 (+ i 1)))
        ((= i len) rotated)
        (vector-set! rotated (modulo (+ i m) len) (vector-ref vec i))))
    )
  )

(rotate '#(10 20 30 40 50 60) 1) ; '#(60 10 20 30 40 50)

(rotate '#(22 18 10 11 6) 3); '#(10 11 6 22 18)


(define (distance-recursive vec x)
  ; inner helper function
  (define (locate vec x i index1 index2)
    (cond ((= i (vector-length vec))
           (if (= index1 -1) -1
               (- index2 index1)))
          ((= (vector-ref vec i) x)
               (if (= index1 -1)
                  (locate vec x (+ i 1) i i)
                  (locate vec x (+ i 1) index1 i)))
          (else
           (locate vec x (+ i 1) index1 index2))))

  ;call inner helper function
  (let ((index1 -1)
        (index2 -1))
        (locate vec x 0 index1 index2)))

(distance-recursive '#(100 22 34 56 22 18 8 22 99 11) 22) ; 6
(distance-recursive '#(5 12 21 34 21 5 89) 34) ; 0

(define (distance-loop vec x)
  (let ((len (vector-length vec)))
    (let ((index1 -1)
          (index2 -1))
      (do ((i 0 (+ i 1)))
          ((= i len) 
           (if (and (not (equal? index1 -1)) (not (equal? index2 -1)))
               (- index2 index1) 
               0))              
        (if (equal? (vector-ref vec i) x)
            (if (equal? index1 -1)  
                (set! index1 i)     
                (set! index2 i))
            (void))) ; else do nothing 
      ))
  )


(distance-loop '#(100 22 34 56 22 18 8 22 99 11) 22) ; 6
(distance-loop '#(5 12 21 34 21 5 89) 34) ; 0



