const TokenKey = 'headline_token';

export const getToken = () => {
  return localStorage.getItem(TokenKey);
};

export const setToken = (token) => {
  localStorage.setItem(TokenKey, token);
};

export const removeToken = () => {
  localStorage.removeItem(TokenKey);
};
