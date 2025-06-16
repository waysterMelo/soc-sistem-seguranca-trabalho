import React from 'react';
import { Doughnut, Line, Bar } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    ArcElement,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    BarElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import { Bell, ChevronLeft, ChevronRight } from 'lucide-react';

// Registra os componentes necessários do Chart.js para que os gráficos funcionem
ChartJS.register(
    ArcElement,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    BarElement,
    Title,
    Tooltip,
    Legend
);

// --- Componentes dos Gráficos ---

// Gráfico 1: Empresas (Rosca)
const CompanyStatusChart = () => {
    const data = {
        labels: ['Ativo', 'Inativo', 'Revisão'],
        datasets: [{
            data: [75, 15, 10],
            backgroundColor: ['#22c55e', '#f97316', '#eab308'],
            borderColor: '#ffffff',
            borderWidth: 4,
            hoverOffset: 8,
        }],
    };

    const options = {
        responsive: true,
        maintainAspectRatio: false,
        cutout: '70%',
        plugins: {
            legend: {
                position: 'bottom',
                labels: {
                    usePointStyle: true,
                    pointStyle: 'circle',
                    padding: 20,
                    color: '#4b5563', // Cor do texto da legenda
                },
            },
        },
    };

    return <Doughnut data={data} options={options} />;
};

// Gráfico 2: ASOs Emitidos (Linha)
const AsoChart = () => {
    const data = {
        labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul'],
        datasets: [{
            label: 'ASOs Emitidos',
            data: [3, 5, 4, 7, 6, 8, 10],
            borderColor: '#3b82f6',
            backgroundColor: 'rgba(59, 130, 246, 0.1)',
            fill: true,
            tension: 0.4,
        }],
    };
    const options = {
        responsive: true,
        maintainAspectRatio: false,
        scales: { y: { beginAtZero: true } },
        plugins: { legend: { display: false } },
    };
    return <Line data={data} options={options} />;
};


// Gráfico 3: Riscos Emitidos por Grupo (Barra Horizontal Empilhada)
const RiskChart = () => {
    const data = {
        labels: [''], // Rótulo vazio para uma única barra
        datasets: [
            {
                label: 'Físicos',
                data: [2],
                backgroundColor: '#22c55e',
                barPercentage: 0.5,
                categoryPercentage: 1.0
            },
            {
                label: 'Acidentes',
                data: [2],
                backgroundColor: '#3b82f6',
                barPercentage: 0.5,
                categoryPercentage: 1.0
            }
        ]
    };

    const options = {
        indexAxis: 'y',
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            x: {
                stacked: true,
                grid: { display: false },
                ticks: {
                    callback: function(value) {
                        // Mostra apenas números inteiros
                        if (Math.floor(value) === value) {
                            return value;
                        }
                    },
                }
            },
            y: {
                stacked: true,
                grid: { display: false },
                ticks: { display: false } // Oculta o rótulo do eixo y
            }
        },
        plugins: {
            tooltip: {
                callbacks: {
                    title: () => null // Oculta o título da tooltip
                }
            },
            legend: {
                position: 'bottom',
                labels: {
                    usePointStyle: true,
                    pointStyle: 'circle',
                    padding: 20,
                    color: '#4b5563',
                }
            }
        }
    };

    return <Bar data={data} options={options} />;
};


// Gráfico 4: Evento eSocial S-2210 (Linha)
const EsocialChart = () => {
    const data = {
        labels: ['Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
        datasets: [{
            label: 'Eventos S-2210',
            data: [1, 0, 2, 1, 3, 0, 1],
            borderColor: '#8b5cf6',
            backgroundColor: 'rgba(139, 92, 246, 0.1)',
            fill: true,
            tension: 0.4,
        }],
    };
    const options = {
        responsive: true,
        maintainAspectRatio: false,
        scales: { y: { beginAtZero: true } },
        plugins: { legend: { display: false } },
    };
    return <Line data={data} options={options} />;
};


// --- Componentes de UI ---

// Componente de Card Genérico
const DashboardCard = ({ title, children, className = '' }) => (
    <div className={`bg-white p-6 rounded-lg shadow-md transition-transform duration-300 hover:-translate-y-1 ${className}`}>
        {title && <h3 className="text-lg font-semibold mb-4 border-b pb-2 text-gray-700">{title}</h3>}
        {children}
    </div>
);

// Botões de filtro de tempo (D, S, M, A)
const TimeFilterButtons = () => (
    <div className="flex space-x-1">
        {['D', 'S', 'M', 'A'].map(filter => (
            <button key={filter} className="px-3 py-1 text-xs font-medium rounded-md bg-gray-100 text-gray-600 hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-gray-400 transition-colors">
                {filter}
            </button>
        ))}
    </div>
);


// --- Componente Principal ---

export default function Dashboard() {
    return (
        <div className="bg-gray-50 min-h-screen font-sans text-gray-800">
            {/* Container Principal */}
            <div className="container mx-auto p-4 sm:p-6 lg:p-8">

                {/* Cabeçalho */}
                <header className="flex justify-between items-start mb-8">
                    <div>
                        <h1 className="text-3xl font-bold text-gray-900">METRA CLOUD</h1>
                        <p className="text-gray-500 mt-1">Sistema de Soluções para gestão de SST e eSocial</p>
                    </div>
                    <button className="p-2 rounded-full text-gray-500 hover:bg-gray-200 hover:text-gray-700 transition-colors">
                        <Bell size={24} />
                    </button>
                </header>

                {/* Grade de Conteúdo do Dashboard */}
                <main className="grid grid-cols-1 md:grid-cols-2 gap-6">

                    {/* Card 1: Vencimento de Documento */}
                    <DashboardCard title="Vencimento de Documento">
                        <div className="space-y-4">
                            <div className="flex justify-between items-center">
                                <div>
                                    <p className="font-medium">LTCAT</p>
                                    <p className="text-sm text-gray-500">WAYSTER HENRIQUE CRUZ DE MELO</p>
                                </div>
                                <span className="bg-red-100 text-red-700 text-xs font-semibold px-2.5 py-0.5 rounded-full">Vencido</span>
                            </div>
                            <div className="flex justify-between items-center">
                                <div>
                                    <p className="font-medium">LTIP</p>
                                    <p className="text-sm text-gray-500">WAYSTER HENRIQUE CRUZ DE MELO</p>
                                </div>
                                <span className="bg-red-100 text-red-700 text-xs font-semibold px-2.5 py-0.5 rounded-full">Vencido</span>
                            </div>
                            <div className="flex justify-between items-center">
                                <div>
                                    <p className="font-medium">PGR</p>
                                    <p className="text-sm text-gray-500">WAYSTER HENRIQUE CRUZ DE MELO</p>
                                </div>
                                <span className="bg-green-100 text-green-700 text-xs font-semibold px-2.5 py-0.5 rounded-full">Válido</span>
                            </div>
                        </div>
                        <div className="text-right mt-4">
                            <a href="#" className="text-sm text-green-600 hover:underline font-medium">ver todos os documentos</a>
                        </div>
                    </DashboardCard>

                    {/* Card 2: Empresas */}
                    <DashboardCard title="Empresas" className="flex flex-col items-center">
                        <div className="w-full h-48 lg:h-56 flex-grow">
                            <CompanyStatusChart />
                        </div>
                    </DashboardCard>

                    {/* Card 3: ASOs Emitidos */}
                    <DashboardCard>
                        <div className="flex justify-between items-center mb-4">
                            <h3 className="text-lg font-semibold text-gray-700">ASOs Emitidos</h3>
                            <TimeFilterButtons />
                        </div>
                        <div className="w-full h-48 lg:h-56">
                            <AsoChart />
                        </div>
                    </DashboardCard>

                    {/* Card 4: EPI's em Vencimento */}
                    <DashboardCard title="EPI's em Vencimento">
                        <div className="text-center text-gray-500 py-10">
                            <p>Nenhum equipamento vencendo.</p>
                        </div>
                        <div className="text-right mt-4">
                            <a href="#" className="text-sm text-green-600 hover:underline font-medium">ver todos os equipamentos</a>
                        </div>
                    </DashboardCard>

                    {/* Card 5: Riscos Emitidos por Grupo */}
                    <DashboardCard title="Riscos Emitidos por Grupo">
                        <div className="w-full h-48 lg:h-56 flex items-end">
                            <RiskChart />
                        </div>
                    </DashboardCard>

                    {/* Card 6: Evento eSocial S-2210 */}
                    <DashboardCard>
                        <div className="flex justify-between items-center mb-4">
                            <h3 className="text-lg font-semibold text-gray-700">Evento eSocial S-2210</h3>
                            <div className="flex items-center space-x-2">
                                <TimeFilterButtons />
                                <div className="flex border rounded-md">
                                    <button className="p-1 text-gray-500 hover:bg-gray-100 border-r"><ChevronLeft size={16}/></button>
                                    <button className="p-1 text-gray-500 hover:bg-gray-100"><ChevronRight size={16}/></button>
                                </div>
                            </div>
                        </div>
                        <div className="w-full h-48 lg:h-56">
                            <EsocialChart />
                        </div>
                    </DashboardCard>

                </main>
            </div>
        </div>
    );
}
