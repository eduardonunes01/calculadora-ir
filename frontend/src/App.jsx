import { useState, useRef } from 'react';
import './App.css';

function App() {
  const [rendimentos, setRendimentos] = useState('');
  const [deducoes, setDeducoes] = useState('');
  const [impostoRetido, setImpostoRetido] = useState('');
  const [resultado, setResultado] = useState(null);
  const [erro, setErro] = useState(null);

  const refDeducoes = useRef(null);
  const refImpostoRetido = useRef(null);

  async function calcular() {
    setErro(null);
    setResultado(null);

    try {
      const resposta = await fetch('http://localhost:8080/calcular', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          rendimentos: Number(rendimentos),
          deducoes: Number(deducoes),
          impostoRetido: Number(impostoRetido)
        })
      });

      const dados = await resposta.json();
      setResultado(dados);
    } catch (e) {
      setErro("Não foi possível realizar a operação.");
    }
  }

  return (
    <div className="pagina">
      <div className="calculadora">
        <h1>Calculadora de Imposto de Renda</h1>
        <p className="subtitulo">
          Informe seus dados abaixo para calcular o imposto.
        </p>

        <div className="campo">
          <label>Rendimentos:</label>
          <input
            type="number"
            value={rendimentos}
            onChange={(e) => setRendimentos(e.target.value)}
            placeholder="Total de rendimentos:"
            onKeyDown={(e) => e.key === 'Enter' && refDeducoes.current.focus()}
          />
        </div>

        <div className="campo">
          <label>Deduções:</label>
          <input
            ref={refDeducoes}
            type="number"
            value={deducoes}
            onChange={(e) => setDeducoes(e.target.value)}
            placeholder="Total de deduções:"
            onKeyDown={(e) => e.key === 'Enter' && refImpostoRetido.current.focus()}
          />
        </div>

        <div className="campo">
          <label>Imposto retido:</label>
          <input
            ref={refImpostoRetido}
            type="number"
            value={impostoRetido}
            onChange={(e) => setImpostoRetido(e.target.value)}
            placeholder="Total de imposto retido:"
            onKeyDown={(e) => e.key === 'Enter' && calcular()}
          />
        </div>

        <button onClick={calcular}>
          Calcular
        </button>

        {erro && (
          <div className="erro">
            {erro}
          </div>
        )}

        {resultado && (
          <div className="resultado">
            <h2>Resultado</h2>
            <pre>{JSON.stringify(resultado, null, 2)}</pre>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;