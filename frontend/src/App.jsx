import { useState, useRef } from 'react';
import './App.css';

function App() {
  const [rendimentos, setRendimentos] = useState('');
  const [deducoes, setDeducoes] = useState('');
  const [impostoRetido, setImpostoRetido] = useState('');
  const [resultado, setResultado] = useState(null);
  const [erro, setErro] = useState(null);
  const [historico, setHistorico] = useState(null);
  const [abaAtiva, setAbaAtiva] = useState('calculadora');

  const refDeducoes = useRef(null);
  const refImpostoRetido = useRef(null);

  const formatarReais = (valor) =>
    Number(valor).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });

  const num = (valor) => Number(valor);

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
          impostoRetido: Number(impostoRetido)
        })
      });

      const dados = await resposta.json();
      setResultado(dados);
    } catch (e) {
      setErro("Não foi possível realizar a operação.");
    }
  }

  async function buscarHistorico() {
    try {
      const resposta = await fetch('http://localhost:8080/calcular/historico');
      const dados = await resposta.json();
      setHistorico(dados);
    } catch (e) {
      setErro("Não foi possível carregar o histórico.");
    }
  }

  function mudarAba(aba) {
    setAbaAtiva(aba);
    if (aba === 'historico') buscarHistorico();
  }

  return (
    <div className="pagina">
      <div className="calculadora">
        <h1>Calculadora de Imposto de Renda</h1>

        <div className="abas">
          <button
            className={abaAtiva === 'calculadora' ? 'aba aba-ativa' : 'aba'}
            onClick={() => mudarAba('calculadora')}
          >
            Calculadora
          </button>
          <button
            className={abaAtiva === 'historico' ? 'aba aba-ativa' : 'aba'}
            onClick={() => mudarAba('historico')}
          >
            Histórico
          </button>
        </div>

        {abaAtiva === 'calculadora' && (
          <>
            <div className="subtitulo">Informe seus dados pra calcular o imposto de renda.</div>
            <div className="campo">
              <label>Rendimentos:</label>
              <input
                type="number"
                value={rendimentos}
                onChange={(e) => setRendimentos(e.target.value)}
                placeholder="Total de rendimentos"
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
                placeholder="Total de deduções"
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
                placeholder="Total de imposto retido"
                onKeyDown={(e) => e.key === 'Enter' && calcular()}
              />
            </div>

            <button onClick={calcular}>Calcular</button>

            {erro && <div className="erro">{erro}</div>}

            {resultado && (
              <div className="resultado">
                <h2>Resultado</h2>
                <div className="linha-resultado">
                  <span>Base de cálculo</span>
                  <span>{formatarReais(resultado.baseCalculo)}</span>
                </div>
                <div className="linha-resultado">
                  <span>Imposto devido</span>
                  <span>{formatarReais(resultado.impostoDevido)}</span>
                </div>
                <div className="linha-resultado">
                  <span>Imposto retido</span>
                  <span>{formatarReais(resultado.impostoRetido)}</span>
                </div>
                <div className={`linha-resultado destaque ${num(resultado.diferenca) < 0 ? 'restituir' : 'pagar'}`}>
                  <span>{num(resultado.diferenca) < 0 ? 'A restituir' : 'A pagar'}</span>
                  <span>{formatarReais(Math.abs(num(resultado.diferenca)))}</span>
                </div>
              </div>
            )}
          </>
        )}

        {abaAtiva === 'historico' && (
          <div className="historico">
            <p className="subtitulo">Verifique aqui seu histórico de cálculo.</p>
            {historico === null && <p className="carregando">Carregando...</p>}
            {historico && historico.length === 0 && (
              <p className="vazio">Nenhum cálculo encontrado.</p>
            )}
            {historico && historico.map((item) => (
              <div key={item.id} className="card-historico">
                <div className="data-historico">
                  {new Date(item.dataHora).toLocaleString('pt-BR')}
                </div>
                <div className="linha-resultado">
                  <span>Rendimentos</span>
                  <span>{formatarReais(item.rendimentos)}</span>
                </div>
                <div className="linha-resultado">
                  <span>Deduções</span>
                  <span>{formatarReais(item.deducoes)}</span>
                </div>
                <div className="linha-resultado">
                  <span>Base de cálculo</span>
                  <span>{formatarReais(item.baseCalculo)}</span>
                </div>
                <div className="linha-resultado">
                  <span>Imposto devido</span>
                  <span>{formatarReais(item.impostoDevido)}</span>
                </div>
                <div className="linha-resultado">
                  <span>Imposto retido</span>
                  <span>{formatarReais(item.impostoRetido)}</span>
                </div>
                <div className={`linha-resultado destaque ${num(item.diferenca) < 0 ? 'restituir' : 'pagar'}`}>
                  <span>{num(item.diferenca) < 0 ? 'A restituir' : 'A pagar'}</span>
                  <span>{formatarReais(Math.abs(num(item.diferenca)))}</span>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default App;