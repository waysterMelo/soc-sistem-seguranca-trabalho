import { useState, useEffect } from 'react';
import { Plus, Search, Pencil, Trash2 } from 'lucide-react';
import { apiService } from '../../../api/apiService';
import { Card } from '../../../components/ui/Card';
import Header, { ActionButton } from '../../../layouts/Header.jsx'; // Reutilizando o Header

export default function EmpresaListPage() {
    const [empresas, setEmpresas] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        setIsLoading(true);
        apiService.getEmpresas(searchTerm).then(data => {
            setEmpresas(data);
            setIsLoading(false);
        });
    }, [searchTerm]);

    return (
        <>
            <Header title="Cadastro de Empresas" subtitle="Gerencie as empresas clientes e fornecedores">
                <ActionButton primary>
                    <Plus size={16} />
                    <span>Nova Empresa</span>
                </ActionButton>
            </Header>

            <Card className="p-0">
                <div className="p-4 border-b border-border">
                    <input
                        type="text"
                        placeholder="Pesquisar por Razão Social ou CNPJ..."
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                        className="w-full max-w-sm p-2 border border-border rounded-lg focus:ring-2 focus:ring-primary/50 outline-none"
                    />
                </div>

                <div className="overflow-x-auto">
                    <table className="w-full text-left">
                        <thead className="bg-light-gray/50">
                        <tr>
                            <th className="p-4 text-sm font-semibold text-gray-custom">Razão Social</th>
                            <th className="p-4 text-sm font-semibold text-gray-custom">CNPJ</th>
                            <th className="p-4 text-sm font-semibold text-gray-custom">Status</th>
                            <th className="p-4 text-sm font-semibold text-gray-custom">Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        {isLoading ? (
                            <tr>
                                <td colSpan="4" className="text-center p-8 text-gray-custom">
                                    Carregando...
                                </td>
                            </tr>
                        ) : (
                            empresas.map(empresa => (
                                <tr key={empresa.id} className="border-b border-border last:border-b-0">
                                    <td className="p-4 font-semibold text-dark">{empresa.razaoSocial}</td>
                                    <td className="p-4 text-gray-custom">{empresa.cnpj}</td>
                                    <td className="p-4">
                                            <span className={`px-2 py-1 text-xs font-bold rounded-full ${
                                                empresa.status === 'Ativo' ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'
                                            }`}>{empresa.status}</span>
                                    </td>
                                    <td className="p-4">
                                        <div className="flex gap-4">
                                            <button className="text-blue-500 hover:text-blue-700"><Pencil size={18}/></button>
                                            <button className="text-danger hover:text-danger-dark"><Trash2 size={18}/></button>
                                        </div>
                                    </td>
                                </tr>
                            ))
                        )}
                        </tbody>
                    </table>
                </div>
            </Card>
        </>
    );
}