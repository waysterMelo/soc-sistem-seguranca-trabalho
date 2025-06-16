const mockEmpresas = [
    { id: 1, razaoSocial: 'TECSEG - SOLUCOES INTEGRADAS LTDA', cnpj: '12345678000199', status: 'Ativo' },
    { id: 2, razaoSocial: 'SAUDE OCUPACIONAL BRASIL S.A.', cnpj: '98765432000111', status: 'Ativo' },
    { id: 3, razaoSocial: 'CONSULTORIA PREVENTIVA EIRELI', cnpj: '11222333000144', status: 'Inativo' },
];

export const apiService = {
    getEmpresas: async (searchTerm = '') => {
        console.log(`Buscando empresas com o termo: "${searchTerm}"`);
        await new Promise(resolve => setTimeout(resolve, 500)); // Simula delay de rede
        if (!searchTerm) {
            return mockEmpresas;
        }
        return mockEmpresas.filter(emp =>
            emp.razaoSocial.toLowerCase().includes(searchTerm.toLowerCase()) ||
            emp.cnpj.includes(searchTerm)
        );
    },
    getEmpresaById: async (id) => {
        console.log(`Buscando empresa com ID: ${id}`);
        await new Promise(resolve => setTimeout(resolve, 500));
        return mockEmpresas.find(emp => emp.id === id);
    },
    // Adicione aqui as funções create, update e delete no futuro
};