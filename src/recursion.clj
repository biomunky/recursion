(ns recursion)

(defn product [coll]
  (if (empty? coll)
    1
    (* (first coll)
       (product (rest coll)))))

(defn singleton? [coll]
  (and
   (not (empty? coll))
    (empty? (rest coll))))

;; have we seen recur yet? Probably not!
(defn my-last [coll]
  (if (next coll)
    (recur (next coll))
    (first coll)))


(defn max-element [a-seq]
  (cond
   (empty? a-seq) nil
   (singleton? a-seq) (first a-seq)
   :else (max (first a-seq) (max-element (rest a-seq)))))


(defn seq-max [seq-1 seq-2]
  (if (> (count seq-1) (count seq-2)) seq-1 seq-2))

(defn longest-sequence [a-seq]
  (cond
   (empty? a-seq) nil
   (singleton? a-seq) (first a-seq)
   :else (seq-max (first a-seq) (longest-sequence (rest a-seq)))))

(defn my-filter [pred? a-seq]
  (if (empty? a-seq)
    a-seq
    (if (pred? (first a-seq))
      (cons (first a-seq) (my-filter pred? (rest a-seq)))
      (my-filter pred? (rest a-seq)))))

(defn sequence-contains? [elem a-seq]
  (cond
   (empty? a-seq) false
   (= (first a-seq) elem) true
   :else (sequence-contains? elem (rest a-seq))))

(defn my-take-while [pred? a-seq]
  (cond
   (empty? a-seq) '()
   (pred? (first a-seq)) (cons (first a-seq) (my-take-while pred? (rest a-seq)))
   :else '()))

(defn my-drop-while [pred? a-seq]
  (cond
   (empty? a-seq) '()
   (pred? (first a-seq)) (my-drop-while pred? (rest a-seq))
   :else a-seq))

(defn seq= [a-seq b-seq]
  (cond
   (and (empty? a-seq) (empty? b-seq)) true
   (or (empty? a-seq) (empty? b-seq)) false
   (= (first a-seq) (first b-seq)) (seq= (rest a-seq) (rest b-seq))
   :else false))

(defn my-map [f seq-1 seq-2]
  (if (or (empty? seq-1) (empty? seq-2)) '()
    (cons
     (f (first seq-1) (first seq-2))
     (my-map f (rest seq-1) (rest seq-2)))))

(defn power [n k]
  (if (== 0 k) 1
    (* n (power n (dec k)))))

(defn fib [n]
  (cond
   (= n 0) 0
   (= n 1) 1
   :else (+ (fib (dec n))
            (fib (- n 2)))))

(defn my-repeat [how-many-times what-to-repeat]
  (if (<= how-many-times 0)
    '()
    (cons what-to-repeat (my-repeat (dec how-many-times) what-to-repeat))))

(defn my-range [up-to]
  (cond
   (== 0 up-to) ()
   :else (cons (dec up-to) (my-range (dec up-to)))))

(defn tails [coll]
  (if (empty? coll) (cons '() coll)
      (cons (seq coll) (tails (rest coll)))))

(defn inits [coll]
  (reverse (map reverse (tails (reverse coll)))))

(defn rotations [coll]
  (if (empty? coll) (cons () nil)
      (let [i (inits coll)
            t (tails coll)]
        (rest (map (fn [a b] (concat a b)) t i)))))

(defn my-frequencies-helper [freqs a-seq]
  (if (empty? a-seq) freqs
    (my-frequencies-helper
     (assoc freqs (first a-seq) (inc (freqs (first a-seq) 0)))
     (rest a-seq))))

(defn my-frequencies [a-seq]
  (my-frequencies-helper {} a-seq))

;; mapcat is cheating - could not cheat and do apply concat ...
(defn un-frequencies [a-map]
  (mapcat (fn [[k v]] (repeat v k)) a-map))

(defn my-take [n coll]
  (if (or (empty? coll) (== 0 n)) ()
    (cons (first coll) (my-take (dec n) (rest coll)))))

(defn my-drop [n coll]
  (if (or (empty? coll) (== 0 n)) coll
      (my-drop (dec n) (rest coll))))

(defn halve [a-seq]
  (let [split-point (int (/ (count a-seq) 2))
        a (my-take split-point a-seq)
        b (my-drop split-point a-seq)]
  [a b]))

(defn seq-merge [a-seq b-seq]
  (cond
   (empty? a-seq) (concat a-seq b-seq)
   (empty? b-seq) (concat a-seq b-seq)
   (< (first a-seq) (first b-seq)) (cons (first a-seq) (seq-merge (rest a-seq) b-seq))
   :else (cons (first b-seq) (seq-merge a-seq (rest b-seq)))))

(defn merge-sort [a-seq]
  (if (<= (count a-seq) 1) a-seq
      (let [[a b] (halve a-seq)] (seq-merge (merge-sort a) (merge-sort b)))))

(defn split-into-monotonics [a-seq]
  [:-])

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])

