import type { Colaborador, ItemFolha, NovoColaborador } from './types';

const BASE_URL = (import.meta.env.VITE_API_URL || 'http://localhost:8080/api/folha').replace(/\/$/, '');
const AUTH_URL = BASE_URL.replace(/\/folha$/, '/auth');
const AUTH_STORAGE_KEY = 'folhaclara_auth';

export type UsuarioLogado = {
  token: string;
  nome: string;
  email: string;
  role: 'ADMIN' | 'RH' | 'FINANCEIRO';
};

export const authStorage = {
  get: (): UsuarioLogado | null => {
    try {
      const raw = localStorage.getItem(AUTH_STORAGE_KEY);
      return raw ? (JSON.parse(raw) as UsuarioLogado) : null;
    } catch {
      return null;
    }
  },
  set: (user: UsuarioLogado) => localStorage.setItem(AUTH_STORAGE_KEY, JSON.stringify(user)),
  clear: () => localStorage.removeItem(AUTH_STORAGE_KEY),
};

export class ApiError extends Error {
  constructor(message: string, readonly status?: number) {
    super(message);
    this.name = 'ApiError';
  }
}

async function request<T>(path: string, init?: RequestInit, baseUrl = BASE_URL): Promise<T> {
  const controller = new AbortController();
  const timeout = window.setTimeout(() => controller.abort(), 12000);
  const token = authStorage.get()?.token;

  try {
    const response = await fetch(`${baseUrl}${path}`, {
      ...init,
      signal: controller.signal,
      headers: {
        Accept: 'application/json',
        ...(init?.body ? { 'Content-Type': 'application/json' } : {}),
        ...(token ? { Authorization: `Bearer ${token}` } : {}),
        ...init?.headers,
      },
    });

    if (!response.ok) {
      let message = `Não foi possível concluir a solicitação (${response.status}).`;
      if (response.status === 401 || response.status === 403) {
        message = path === '/login'
          ? 'E-mail ou senha inválidos.'
          : 'Sessão expirada ou sem permissão. Faça login novamente.';
      }
      try {
        const body: unknown = await response.json();
        if (typeof body === 'object' && body !== null && 'message' in body && typeof body.message === 'string') {
          message = body.message;
        }
      } catch {
        /* resposta sem JSON */
      }
      throw new ApiError(message, response.status);
    }

    if (init?.method === 'DELETE' || response.status === 204) return undefined as T;

    return (await response.json()) as T;
  } catch (error) {
    if (error instanceof ApiError) throw error;
    if (error instanceof DOMException && error.name === 'AbortError') {
      throw new ApiError('A conexão demorou demais. Verifique o backend e tente novamente.');
    }
    throw new ApiError('Não foi possível conectar à API. Confirme se o backend está ativo em localhost:8080 e se o CORS permite localhost:5173.');
  } finally {
    window.clearTimeout(timeout);
  }
}

type ResumoFolhaSeguro = { quantidadeColaboradores: number; valorTotalFolha: number };

export const api = {
  login: (email: string, senha: string) =>
    request<UsuarioLogado>('/login', { method: 'POST', body: JSON.stringify({ email, senha }) }, AUTH_URL),

  colaboradores: () => request<Colaborador[]>('/colaboradores'),
  detalhada: (periodo: string) => request<ItemFolha[]>(`/detalhada?periodo=${encodeURIComponent(periodo)}`),
  resumo: (periodo: string) => request<ResumoFolhaSeguro>(`/resumo?periodo=${encodeURIComponent(periodo)}`),
  cadastrar: (data: NovoColaborador) => request<Colaborador>('/colaboradores', { method: 'POST', body: JSON.stringify(data) }),
  atualizar: (matricula: string, data: NovoColaborador) => request<Colaborador>(`/colaboradores/${matricula}`, { method: 'PUT', body: JSON.stringify(data) }),
  excluir: (matricula: string) => request<void>(`/colaboradores/${matricula}`, { method: 'DELETE' }),
};