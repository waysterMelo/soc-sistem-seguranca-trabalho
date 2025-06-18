import React, {useState} from 'react';
import {AlertCircle, Building, Check, FileText, HeartPulse, Stethoscope, User} from 'lucide-react';


const FormSection = ({ title, children, className = '', icon }) => (
    <div className={`bg-white p-6 rounded-lg shadow-xs border border-gray-100 ${className}`}>
        <div className="flex items-center gap-3 pb-4 mb-6 border-b border-gray-100">
            {icon && <div className="text-blue-600">{icon}</div>}
            {title && <h3 className="text-lg font-semibold text-gray-800">{title}</h3>}
        </div>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
            {children}
        </div>
    </div>
);

const FormField = ({ label, number, required, children, className = '', error }) => (
    <div className={`flex flex-col gap-2 ${className}`}>
        <label className="flex items-center gap-1 text-sm font-medium text-gray-700">
            {number && (
                <span className="flex items-center justify-center w-6 h-6 text-xs font-bold text-white bg-blue-600 rounded-full">
                    {number}
                </span>
            )}
            {label}
            {required && <span className="text-red-500">*</span>}
        </label>
        <div className={error ? "border-red-500 rounded-md" : ""}>
            {children}
        </div>
        {error && <p className="flex items-center gap-1 mt-1 text-xs text-red-500"><AlertCircle size={14}/> {error}</p>}
    </div>
);

const InputWithActions = ({ placeholder, value, actions, valid, error }) => (
    <div className="relative">
        <input
            type="text"
            placeholder={placeholder}
            defaultValue={value}
            className={`w-full py-2.5 pl-4 pr-28 border ${
                error ? 'border-red-500' : valid ? 'border-green-500' : 'border-gray-300'
            } rounded-lg focus:outline-none focus:ring-2 ${
                error ? 'focus:ring-red-200' : 'focus:ring-blue-100'
            } transition-colors`}
        />
        <div className="absolute right-0 top-0 h-full flex items-center">
            {valid && <Check size={18} className="text-green-500 mx-2" />}
            <div className="flex h-full bg-gray-50 rounded-r-lg overflow-hidden border-l">
                {actions}
            </div>
        </div>
    </div>
);

const RadioGroup = ({ name, options, selected, onChange, className = "" }) => (
    <div className={`flex flex-wrap gap-4 ${className}`}>
        {options.map(opt => (
            <label
                key={opt.value}
                className="flex items-center text-sm cursor-pointer"
            >
                <div className="relative">
                    <input
                        type="radio"
                        name={name}
                        value={opt.value}
                        checked={selected === opt.value}
                        onChange={onChange}
                        className="sr-only"
                    />
                    <div className={`w-4 h-4 rounded-full border-2 flex items-center justify-center transition-colors ${
                        selected === opt.value
                            ? 'border-blue-600 bg-blue-600'
                            : 'border-gray-300'
                    }`}>
                        {selected === opt.value && (
                            <div className="w-2 h-2 bg-white rounded-full"></div>
                        )}
                    </div>
                </div>
                <span className="ml-2 text-gray-700">{opt.label}</span>
            </label>
        ))}
    </div>
);

const TabButton = ({ label, isActive, onClick, icon }) => (
    <button
        type="button"
        onClick={onClick}
        className={`px-5 py-3 flex items-center gap-2 text-sm font-medium whitespace-nowrap transition-all ${
            isActive
                ? 'text-blue-600 border-b-2 border-blue-600 bg-blue-50'
                : 'text-gray-500 hover:text-gray-700'
        }`}
    >
        {icon && <span>{icon}</span>}
        {label}
    </button>
);

// --- CONTEÚDO DAS ABAS ATUALIZADO ---

const TabEmpregador = () => (
    <div className="flex flex-col items-center justify-center p-10 bg-gray-50 rounded-lg border border-dashed border-gray-300">
        <Building size={48} className="text-gray-400 mb-4" />
        <h3 className="text-lg font-medium text-gray-700 mb-2">Dados do Empregador</h3>
        <p className="text-gray-500 text-center max-w-md">
            Informações serão carregadas automaticamente após selecionar o funcionário
        </p>
    </div>
);

// ... (implementações semelhantes para outras abas com melhorias visuais)

// --- COMPONENTE PRINCIPAL COM TAB NAVIGATION MELHORADA ---

const CATForm = () => {
    const [activeTab, setActiveTab] = useState('empregador');
    const [formData, setFormData] = useState({
        area: 'urbana',
        aposentado: 'nao',
        tipo_acidente: 'tipico',
        afastamento: 'sim',
        lateralidade: 'na',
        policial: 'nao',
        obito_acidente: 'nao'
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const tabs = [
        { id: 'empregador', label: 'Empregador', icon: <Building size={18} /> },
        { id: 'acidentado', label: 'Acidentado', icon: <User size={18} /> },
        { id: 'acidente', label: 'Acidente/Doença', icon: <Stethoscope size={18} /> },
        { id: 'atestado', label: 'Atestado', icon: <FileText size={18} /> },
        { id: 'cid', label: 'CID', icon: <HeartPulse size={18} /> }
    ];

    return (
        <div className="max-w-7xl mx-auto p-4 bg-gray-50">
            <div className="bg-white rounded-xl shadow-sm overflow-hidden">
                {/* Header */}
                <div className="bg-gradient-to-r from-blue-50 to-indigo-50 px-8 py-6 border-b">
                    <div className="flex flex-col md:flex-row md:items-center justify-between gap-4">
                        <div>
                            <h1 className="text-2xl font-bold text-gray-800">Comunicação de Acidente de Trabalho (CAT)</h1>
                            <p className="text-gray-600 mt-1">Preencha todos os campos obrigatórios para emissão do documento</p>
                        </div>
                        <div className="flex gap-3">
                            <button className="px-4 py-2.5 bg-white border border-gray-300 rounded-lg text-gray-700 font-medium hover:bg-gray-50 transition-colors">
                                Cancelar
                            </button>
                            <button className="px-4 py-2.5 bg-blue-600 border border-blue-700 rounded-lg text-white font-medium hover:bg-blue-700 transition-colors flex items-center gap-2">
                                <Check size={18} />
                                Salvar CAT
                            </button>
                        </div>
                    </div>
                </div>

                {/* Tab Navigation */}
                <div className="flex overflow-x-auto no-scrollbar border-b">
                    <div className="flex min-w-max">
                        {tabs.map(tab => (
                            <TabButton
                                key={tab.id}
                                label={tab.label}
                                icon={tab.icon}
                                isActive={activeTab === tab.id}
                                onClick={() => setActiveTab(tab.id)}
                            />
                        ))}
                    </div>
                </div>

                {/* Progress Indicator */}
                <div className="px-8 py-4 bg-blue-50 flex items-center">
                    <div className="flex-1">
                        <div className="h-1.5 bg-blue-200 rounded-full overflow-hidden">
                            <div className="h-full bg-blue-600 w-3/5"></div>
                        </div>
                    </div>
                    <span className="ml-4 text-sm font-medium text-blue-700">60% preenchido</span>
                </div>

                {/* Tab Content */}
                <div className="p-8">
                    {activeTab === 'empregador' && <TabEmpregador />}
                    {activeTab === 'acidentado' && (
                        <TabAcidentado
                            formData={formData}
                            handleChange={handleChange}
                        />
                    )}
                    {/* ... outras abas */}
                </div>

                {/* Footer */}
                <div className="bg-gray-50 px-8 py-5 border-t flex flex-col sm:flex-row justify-between gap-4">
                    <div className="text-sm text-gray-600 flex items-center">
                        <AlertCircle size={16} className="mr-2 text-yellow-500" />
                        Todos os campos marcados com * são obrigatórios
                    </div>
                    <div className="flex gap-3">
                        <button className="px-4 py-2.5 bg-gray-200 rounded-lg text-gray-700 font-medium hover:bg-gray-300 transition-colors">
                            Voltar
                        </button>
                        <button className="px-4 py-2.5 bg-blue-600 rounded-lg text-white font-medium hover:bg-blue-700 transition-colors">
                            Próxima Etapa
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CATForm;