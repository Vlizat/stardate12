let casa = 0;
let timer;
let contagemAtiva = false;
let casas = [0]; // Array para armazenar as casas (cada casa é um valor)

const casaDisplay = document.getElementById('casa');
const button2 = document.getElementById('button2');
const button3 = document.getElementById('button3');
const button4 = document.getElementById('button4');
const button5 = document.getElementById('button5');
const button6 = document.getElementById('button6');
const button7 = document.getElementById('button7');
const button8 = document.getElementById('button8');

function atualizarDisplay() {
    casaDisplay.textContent = formatarCasa(casas);
}

function formatarCasa(casas) {
    let valorFormatado = casas.join('');
    return valorFormatado.replace(/\B(?=(\d{3})+(?!\d))/g, '.'); // Adiciona os pontos entre as casas
}

button2.addEventListener('click', () => {
    casas.push(0); // Aumenta uma casa
    atualizarDisplay();
    if (casas.length > 1) {
        iniciarContagem();
    }
});

button3.addEventListener('click', () => {
    casas = [0]; // Reseta a contagem
    atualizarDisplay();
});

button4.addEventListener('click', () => {
    if (casas.length > 1) {
        casas.pop(); // Diminui uma casa
        atualizarDisplay();
    }
});

button5.addEventListener('click', () => {
    casas[casas.length - 1]++; // Aumenta o valor da casa atual
    atualizarDisplay();
    if (!contagemAtiva) iniciarContagem();
});

button6.addEventListener('click', () => {
    if (casas[casas.length - 1] > 0) {
        casas[casas.length - 1]--; // Diminui o valor da casa atual
        atualizarDisplay();
        if (!contagemAtiva) iniciarContagem();
    }
});

button7.addEventListener('click', () => {
    casas = [0]; // Reseta tudo
    atualizarDisplay();
    clearInterval(timer); // Para a contagem regressiva
    contagemAtiva = false;
});

button8.addEventListener('click', () => {
    if (!contagemAtiva) {
        iniciarContagem();
    }
});

function iniciarContagem() {
    contagemAtiva = true;
    timer = setInterval(() => {
        for (let i = casas.length - 1; i >= 0; i--) {
            if (casas[i] > 0) {
                casas[i]--;
                atualizarDisplay();
                break;
            } else {
                casas[i] = 9; // Resetar a casa para 9 se for 0
            }
        }
        // Verificar se todas as casas chegaram a zero
        if (casas.every(casa => casa === 0)) {
            clearInterval(timer);
            contagemAtiva = false;
        }
    }, 1000); // Atualiza a cada 1 segundo
}
