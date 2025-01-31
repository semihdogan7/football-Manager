import React from 'react';
import { Radar } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    RadialLinearScale,
    PointElement,
    LineElement,
    Filler,
    Tooltip,
    Legend,
} from 'chart.js';

// Chart.js modüllerini kaydediyoruz
ChartJS.register(RadialLinearScale, PointElement, LineElement, Filler, Tooltip, Legend);

const RadarChart = ({ attributes }) => {
    // Oyuncu değerlerini ve etiketlerini oluştur
    const labels = Object.keys(attributes);
    const dataValues = Object.values(attributes);

    const data = {
        labels: labels, // Öznitelik isimleri
        datasets: [
            {
                label: 'Oyuncu Özellikleri',
                data: dataValues, // Özellik değerleri
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgb(54, 235, 69)',
                borderWidth: 4,
            },
        ],
    };

    const options = {
        scales: {
            r: {
                min: 0, // Y ekseni minimum değer
                max: 20, // Y ekseni maksimum değer
                ticks: {
                    display: false, // Eksen değerlerini gizler
                    stepSize: 5, // Her bir halkada artış miktarı (adım boyutu)

                },
                grid: {
                    color: 'rgba(213, 213, 213, 0.5)', // Grid çizgilerinin rengi

                    // circular: true, // Grid çizgilerini dairesel yapar (isteğe bağlı)
                },
                beginAtZero: true, // Tüm değerler 0'dan başlar
            },
        },
        plugins: {
            legend: {
                display: false, // Legend göstermek için
            },
        },
    };

    return <Radar data={data} options={options} />;
};

export default RadarChart;
