# space_minicard_game
Mini card game for HKUSPACE Assignment

![layout](https://user-images.githubusercontent.com/20796385/132431095-c4f1fdce-c337-4975-9000-cdf579beb17d.jpeg)

• The screen starts with showing 2 play card back indicating it’s about to draw two 
random cards. The cards display are image resources. It’s stored as an array of image 
resource ID. You can download the required play cards images from icons8.com or 
other website

• Each time a user taps “PLAY” button, it generates two random number. And these two 
random number are used as array index to display the card images resources stored by 
array.

• If as player gets a pair of “A”s, he/she wins 50 points. If a player gets a pair of J, Q, K, 
he/she wins 20 points. If a player gets a pair of 10, he/she wins 10 points. If a player 
gets any other pairs of cards, he/she wins 5 point. If a player gets one A, he/she get 2
points. Otherwise, he/she loses 1 point.
