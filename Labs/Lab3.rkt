#lang racket
(define (first-unique L)
  (letrec ((count-element
            (lambda (lst elem)
              (if (null? lst)
                  0
                  (if (eq? elem (car lst))
                      (+ 1 (count-element (cdr lst) elem))
                      (count-element (cdr lst) elem)))))

           (iterate
            (lambda (lst elem)
              (if (null? lst)
                  0
                  (let ((num (count-element lst elem)))
                    (if (< num 1)
                        elem
                        (iterate (cdr lst) (car lst)))))))
           )

    (iterate L (car L))
    ))


(first-unique '(18 22 17 19 21 18 17)) ; 22
(first-unique '(7 2 2 1 8 1)) ; 7


(define (reverse-string str)
  (let ((char-list (string->list str))
        (reversed-list (reverse (string->list str))))
    (list->string reversed-list))
  )


(reverse-string "rahim") ; mihar
(reverse-string "extension") ; noisnetxe

(define (all-substrings str1 n)
  (let loop ((index 0)
             (substr '()))
    (if (<= index (- (string-length str1) n))
        (loop (+ index 1)
              (cons (substring str1 index (+ index n)) substr))
        (reverse substr))))

(all-substrings "Rahim" 2) ; ("Ra" "ah" "hi" "im")

(all-substrings "Green" 5) ; Green



    
