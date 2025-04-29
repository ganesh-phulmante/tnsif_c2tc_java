function renderDice(diceElement, score) {
    diceElement.innerHTML = '';
    const dotPositions = {
      1: [4],
      2: [0, 8],
      3: [0, 4, 8],
      4: [0, 2, 6, 8],
      5: [0, 2, 4, 6, 8],
      6: [0, 2, 3, 5, 6, 8],
    };
  
    for (let i = 0; i < 9; i++) {
      const dot = document.createElement('div');
      dot.classList.add('dot');
      if (!dotPositions[score].includes(i)) {
        dot.classList.add('hidden');
      }
      diceElement.appendChild(dot);
    }
  }
  
  function rollDice() {
    const scores = [];
    for (let i = 1; i <= 3; i++) {
      const score = Math.floor(Math.random() * 6) + 1;
      scores.push(score);
      renderDice(document.getElementById(`dice${i}`), score);
    }
  
    const maxScore = Math.max(...scores);
    const winners = [];
    scores.forEach((score, index) => {
      if (score === maxScore) {
        winners.push(document.querySelector(`#player${index + 1} h3`).textContent);
      }
    });
  
    const result = document.getElementById('result');
    if (winners.length === 1) {
      result.textContent = `${winners[0]} wins with a score of ${maxScore}!`;
    } else {
      result.textContent = `It's a tie between ${winners.join(' and ')} with a score of ${maxScore}!`;
    }
  }
  