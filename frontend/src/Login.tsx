import { useState, type FormEvent } from 'react';
import { Activity, ArrowRight, Eye, EyeOff, Lock, Mail } from 'lucide-react';
import { api, ApiError, type UsuarioLogado } from './api';

type LoginProps = {
  onLogin: (user: UsuarioLogado) => void;
};

export function Login({ onLogin }: LoginProps) {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [mostrarSenha, setMostrarSenha] = useState(false);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  async function handleSubmit(e: FormEvent) {
    e.preventDefault();
    if (!email.trim() || !senha.trim()) {
      setError('Informe seu e-mail e sua senha.');
      return;
    }

    setError('');
    setLoading(true);
    try {
      const user = await api.login(email.trim(), senha);
      onLogin(user);
    } catch (err) {
      setError(err instanceof ApiError ? err.message : 'Não foi possível entrar. Verifique suas credenciais.');
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="login-page">
      <div className="login-card">
        <div className="login-brand">
          <span className="brand-name">
  folha
  <span className="brand-name">folha<span>pay</span></span>
</span>
        </div>

        <h1>Acessar sistema</h1>
        <p className="login-subtitle">
          Entre com suas credenciais corporativas para gerenciar a folha de pagamento.
        </p>

        {error && <div className="login-error" role="alert">{error}</div>}

        <form className="login-form" onSubmit={handleSubmit} noValidate>
          <div className="field">
            <label htmlFor="login-email">E-mail</label>
            <div className="input-wrap">
              <span><Mail size={15} /></span>
              <input
                id="login-email"
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                autoComplete="email"
                required
              />
            </div>
          </div>

          <div className="field">
            <label htmlFor="login-senha">Senha</label>
            <div className="input-wrap">
              <span><Lock size={15} /></span>
              <input
                id="login-senha"
                type={mostrarSenha ? 'text' : 'password'}
                value={senha}
                onChange={(e) => setSenha(e.target.value)}
                autoComplete="current-password"
                required
              />
              <button
                type="button"
                onClick={() => setMostrarSenha((prev) => !prev)}
                aria-label={mostrarSenha ? 'Ocultar senha' : 'Mostrar senha'}
                style={{
                  border: 0,
                  background: 'transparent',
                  color: 'var(--muted)',
                  padding: '0 10px',
                  display: 'grid',
                  placeItems: 'center',
                  cursor: 'pointer',
                }}
              >
                {mostrarSenha ? <EyeOff size={15} /> : <Eye size={15} />}
              </button>
            </div>
          </div>

          <button type="submit" className="button primary login-submit" disabled={loading}>
            {loading ? 'Entrando...' : <>Entrar <ArrowRight size={16} /></>}
          </button>
        </form>
      </div>
    </div>
  );
}