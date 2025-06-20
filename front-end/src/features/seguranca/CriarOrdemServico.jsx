import React from 'react';

// --- ÍCONES ---
// Ícones reutilizados e novos para esta tela.

const SearchIcon = () => (
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="1.5" stroke="currentColor" className="w-5 h-5">
        <path strokeLinecap="round" strokeLinejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
    </svg>
);

const ClearIcon = () => (
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth="2" stroke="currentColor" className="w-5 h-5">
        <path strokeLinecap="round" strokeLinejoin="round" d="M6 18L18 6M6 6l12 12" />
    </svg>
);

const InfoIcon = () => (
    <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4 text-gray-400" viewBox="0 0 20 20" fill="currentColor">
        <path fillRule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clipRule="evenodd" />
    </svg>
);


// --- SUB-COMPONENTES DO FORMULÁRIO ---

const SectionTitle = ({ title, children }) => (
    <div className="flex justify-between items-center mb-2">
        <h3 className="text-md font-semibold text-gray-700">{title}</h3>
        {children && <div>{children}</div>}
    </div>
);

const FormSection = ({ children }) => (
    <div className="mb-6">
        {children}
    </div>
);

const InputField = ({ label, value }) => (
    <div>
        <label className="block text-sm font-medium text-gray-600 mb-1">{label}</label>
        <input type="text" defaultValue={value} className="w-full p-2 border border-gray-300 rounded-md bg-gray-50" readOnly/>
    </div>
);

const TextAreaField = ({ label, placeholder }) => (
    <div>
        <label className="block text-sm font-medium text-gray-600 mb-1">{label}</label>
        <textarea placeholder={placeholder} className="w-full p-2 border border-gray-300 rounded-md" rows="3"></textarea>
    </div>
);

// --- COMPONENTE PRINCIPAL ---

function CriarOrdemDeServico() {
    return (
        <div className="p-4 sm:p-6 md:p-8 bg-white min-h-screen" style={{ fontFamily: "'Inter', sans-serif" }}>
            <div className="max-w- mx-auto">
                <header className="mb-8">
                    <h1 className="text-2xl font-bold text-gray-800">Criar Ordem de Serviço de Saúde e Segurança no Trabalho</h1>
                </header>

                <main>
                    {/* Informações do Funcionário */}
                    <FormSection>
                        <SectionTitle title="Informações do Funcionário" />
                        <div className="p-4 border border-gray-200 rounded-lg">
                            <label className="block text-sm font-medium text-gray-600 mb-1">Filtrar por Registros Profissionais</label>
                            <div className="flex items-center gap-2">
                                <input
                                    type="text"
                                    defaultValue="Marina Garcia Lopes - MARINA GARCIA LOPES CONS EM TEC DA INFOR LTDA - Comercial e Projetos - Gerente Comercial e de Projetos"
                                    className="flex-grow w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500 bg-gray-50"
                                    readOnly
                                />
                                <button className="p-2 text-white bg-green-500 rounded-md hover:bg-green-600 flex-shrink-0 shadow-sm">
                                    <SearchIcon />
                                </button>
                                <button className="p-2 text-white bg-red-500 rounded-md hover:bg-red-600 flex-shrink-0 shadow-sm">
                                    <ClearIcon />
                                </button>
                            </div>
                        </div>
                    </FormSection>

                    {/* Dados da Ordem de Serviço */}
                    <FormSection>
                        <SectionTitle title="Dados da Ordem de Serviço" />
                        <div className="p-4 border border-gray-200 rounded-lg space-y-4">
                            <div>
                                <label className="block text-sm font-medium text-gray-600 mb-1">Data de Elaboração <span className="text-red-500">*</span></label>
                                <input type="text" defaultValue="06/06/2025" className="w-full md:w-1/3 p-2 border border-gray-300 rounded-md"/>
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-600 mb-1">Descrição da Ordem de Serviço</label>
                                <textarea
                                    className="w-full p-2 border border-gray-300 rounded-md"
                                    rows="6"
                                    defaultValue="Pela presente Ordem de Serviço objetivamos informar os trabalhadores que executam suas atividades laborais nesse setor, conforme estabelece a NR-1, item 1.7, sobre as condições de segurança e saúde, bem como aos riscos aos quais estão expostos, como medida preventiva e tendo como parâmetro os agentes físicos, químicos, e biológicos citados na NR-9 - Programa de Prevenção de Riscos Ambientais (Lei nº 6.514 de 22/12/1977, Portaria nº 3.214 de 08/06/1978), bem como os procedimentos de aplicação da NR-6 - Equipamento de Proteção Individual - EPI, NR-17 - Ergonomia, de forma a padronizar comportamentos para prevenir acidentes e/ou doenças ocupacionais."
                                />
                                <div className="flex items-center mt-2">
                                    <input id="exibir-descricao" type="checkbox" className="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500" />
                                    <label htmlFor="exibir-descricao" className="ml-2 block text-sm text-gray-900">Exibir Descrição do Setor</label>
                                </div>
                            </div>
                            <div>
                                <label className="block text-sm font-medium text-gray-600 mb-1">Descrição da Função</label>
                                <textarea
                                    className="w-full p-2 border border-gray-300 rounded-md bg-gray-50"
                                    rows="2"
                                    readOnly
                                    defaultValue="Trabalho híbrido home office e escritório, com envio de propostas, e acompanhamento de projetos de tecnologia."
                                />
                            </div>
                        </div>
                    </FormSection>

                    {/* Riscos e EPI's Associados */}
                    <FormSection>
                        <SectionTitle title="Riscos e EPI's Associados" />
                        <div className="p-4 border border-gray-200 rounded-lg text-sm text-gray-700">
                            O funcionário não está exposto a nenhum risco.
                        </div>
                    </FormSection>

                    {/* Equipamentos Adicionais */}
                    <FormSection>
                        <div className="flex items-center mb-2">
                            <h3 className="text-md font-semibold text-gray-700 mr-2">Equipamentos Adicionais</h3>
                            <InfoIcon />
                        </div>
                        <div className="flex items-center gap-2">
                            <input
                                type="text"
                                placeholder="Selecione um equipamento funcional"
                                className="flex-grow w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                            />
                            <button className="p-2 text-white bg-green-500 rounded-md hover:bg-green-600 flex-shrink-0 shadow-sm">
                                <SearchIcon />
                            </button>
                        </div>
                    </FormSection>

                    {/* Informações Complementares */}
                    <FormSection>
                        <SectionTitle title="Informações Complementares" />
                        <div className="p-4 border border-gray-200 rounded-lg space-y-4">
                            <div>
                                <SectionTitle title="Medidas Preventivas" />
                                <textarea placeholder="Digite as medidas preventivas" className="w-full p-2 border border-gray-300 rounded-md" rows="3"></textarea>
                            </div>
                            <div>
                                <SectionTitle title="Recomendações" />
                                <textarea placeholder="Digite as recomendações" className="w-full p-2 border border-gray-300 rounded-md" rows="3"></textarea>
                            </div>
                            <div>
                                <SectionTitle title="Observações" />
                                <textarea placeholder="Digite uma observação para a ordem de serviço" className="w-full p-2 border border-gray-300 rounded-md" rows="3"></textarea>
                            </div>
                        </div>
                    </FormSection>

                    {/* Botões de Ação */}
                    <div className="flex justify-end gap-3 mt-8">
                        <button className="font-semibold py-2 px-6 rounded-lg bg-red-600 text-white hover:bg-red-700">Cancelar</button>
                        <button className="font-semibold py-2 px-6 rounded-lg bg-green-600 text-white hover:bg-green-700">Salvar</button>
                    </div>
                </main>
            </div>
        </div>
    );
}


// Componente App para renderização final.
export default CriarOrdemDeServico
