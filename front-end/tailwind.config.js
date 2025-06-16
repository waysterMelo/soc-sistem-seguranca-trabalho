/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./index.html",
        "./src/** /*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {
            colors: {
                primary: '#009245',   // Verde da segurança do trabalho
                secondary: '#00692D', // Verde escuro
                tertiary: '#4CAF50',  // Verde médio
                danger: '#D32F2F',    // Vermelho da segurança do trabalho
                'danger-light': '#FF6659',
                'danger-dark': '#9A0007',
                light: '#E8F5E9',     // Verde claro para fundos
                dark: '#1B5E20',      // Verde muito escuro
                'light-gray': '#F5F5F5',
                'gray-custom': '#707070',
                border: '#E4E7EC',
            },
            fontFamily: {

                sans: ['Poppins', 'sans-serif'],
            },
        },
    },
    plugins: [],
}