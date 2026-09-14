import { useState } from 'react';

function App() {
  const [rendimentos, setRendimentos] = useState('');
  const [deducoes, setDeducoes] = useState('');
  const [impostoRetido, setImpostoRetido] = useState('');
  const [resultado, setResultado] = useState<any>(null);
  const [erro, setErro] = useState<string | null>(null);

  async function calcular() {
    setErro(null);
    setResultado(null);

    try {
      const resposta = await fetch('http://localhost:8080/calcular', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          rendimentos: Number(rendimentos),
          deducoes: Number(deducoes),
          impostoRetido: Number(impostoRetido),
        }),
      });

      if (!resposta.ok) {
        const mensagem = await resposta.text();
        throw new Error(mensagem || 'Erro ao calcular o imposto.');
      }

      const dados = await resposta.json();
      setResultado(dados);
    } catch (e) {
      setErro(e.message);
    }
  }

  return (
    <div style={{ maxWidth: 400, margin: '40px auto', fontFamily: 'sans-serif' }}>
      <h1>Calculadora de IR</h1>

      <div>
        <div>
          <label>Rendimentos: </label>
          <input
            type="number"
            step="0.01"
            value={rendimentos}
            onChange={(e) => setRendimentos(e.target.value)}
          />
        </div>

        <div>
          <label>Deduções: </label>
          <input
            type="number"
            step="0.01"
            value={deducoes}
            onChange={(e) => setDeducoes(e.target.value)}
          />
        </div>

        <div>
          <label>Imposto retido: </label>
          <input
            type="number"
            step="0.01"
            value={impostoRetido}
            onChange={(e) => setImpostoRetido(e.target.value)}
          />
        </div>

        <button onClick={calcular}>Calcular</button>
      </div>

      {erro && <p style={{ color: 'red' }}>{erro}</p>}

      {resultado && (
        <div>
          <h2>Resultado</h2>
          <p>Base de cálculo: R$ {resultado.baseCalculo}</p>
          <p>Imposto devido: R$ {resultado.impostoDevido}</p>
          <p>Imposto retido: R$ {resultado.impostoRetido}</p>
          <p>Diferença: R$ {resultado.diferenca}</p>
        </div>
      )}
    </div>
  );
}

export default App;