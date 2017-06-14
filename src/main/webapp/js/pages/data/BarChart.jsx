import React, {Component} from 'react';
import Header from '../../components/Header.jsx';
import Multiselect from 'react-widgets/lib/Multiselect';
import DateTimePicker from 'react-widgets/lib/DateTimePicker';

import { scaleLinear } from 'd3-scale';
import { scaleOrdinal } from 'd3-scale';
import { max } from 'd3-array';
import { select } from 'd3-selection';
import { axisBottom } from 'd3-axis';
import { axisLeft } from 'd3-axis';


const demoData = [
    {x: 'A', y: 0.04167},
    {x: 'B', y: 0.01492},
    {x: 'C', y: 0.02782},
    {x: 'D', y: 0.01782},
    {x: 'E', y: 0.00582}
];

//react widgets http://jquense.github.io/react-widgets/docs/#/?_k=h010sg
//D3 tutorial https://bost.ocks.org/mike/bar/3/
//https://bl.ocks.org/mbostock/3885304
class BarChart extends Component {

    constructor(props){
        super(props);
        this.state = {
            stations : [],
            selectedStations : [],
            fields: [],
            selectedFields: [],
            data: demoData,
            size: [1000,500]
        };
        this.createBarChart = this.createBarChart.bind(this);
    }
    componentDidMount() {
        this.createBarChart()
    }
    componentDidUpdate() {
        this.createBarChart()
    }
    createBarChart() {
        const node = this.node;

        const yData = $.map(this.state.data, d=>d.y)
        const yDataMax = max(yData);
        const margin = {top: 20, right: 30, bottom: 30, left: 40},
            width = this.state.size[0] - margin.left - margin.right,
            height = this.state.size[1] - margin.top - margin.bottom;

        const chart = select(node);
        chart
            .attr('width', width + margin.left + margin.right)
            .attr('height', height + margin.top + margin.bottom);

        const barWidth = width / this.state.data.length;
        const barPadding = 1.02;



        const yScale = scaleLinear()
            .domain([0, yDataMax])
            .range([0, height]);

        const xScale = scaleOrdinal()
            .domain([0, this.state.data.length])
            .range([0, width]);


        const xAxis = axisBottom(xScale);
        const yAxis = axisLeft(yScale);

        chart.append("g")
            .attr("class", "x axis")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis);

        chart.append("g")
            .attr("class", "y axis")
            .call(yAxis);

        chart
            .selectAll('rect')
            .data(yData)
            .enter()
            .append('rect')
                .attr('class', 'bar')
                .attr('x', (d,i) => i * barWidth * barPadding)
                .attr('y', d => height - yScale(d))
                .attr('height', d => yScale(d))
                .attr('width', barWidth);


    }

    componentDidMount(){
        $.ajax({
            type: 'GET',
            cache: false,
            url: '/api/data/metadata/stations'
        }).done(data=>{
            this.setState({
                stations: $.map(data, (d)=> {
                    return {
                        id: d.id,
                        name: `${d.code} ${d.name}`
                    };
                })
            });
        });
        $.ajax({
            type: 'GET',
            cache: false,
            url: '/api/data/metadata/fielddescriptions'
        }).done(data=>{
            this.setState({
                fields: $.map(data, (d)=> {
                   return {
                       id: d.id,
                       name: `${d.description} (${d.tableName} - ${d.columnName})'`
                   };
                })
            });
        });
    }

    render(){
        return <div>
            <Header/>
            <div className="row">
                <div className="col-lg-4">
                    Станции
                    <Multiselect
                        data={this.state.stations}
                        valueField = "id"
                        textField = "name"
                        filter = "contains"
                        caseSensitive = {false}
                        onChange={value => this.setState({selectedStations: value})}
                    />
                </div>
                <div className="col-lg-8">
                    Типы данных
                    <Multiselect
                        data={this.state.fields}
                        valueField = "id"
                        textField = "name"
                        filter = "contains"
                        caseSensitive = {false}
                        onChange={value => this.setState({selectedFields: value})}
                    />
                </div>
            </div>
            <div className="row">
                <div className="col-lg-2">
                    Начальная дата
                    <DateTimePicker time={false} defaultValue={null}/>
                </div>
                <div className="col-lg-2">
                    Конечная дата
                    <DateTimePicker time={false} defaultValue={null}/>
                </div>
            </div>
            <svg ref={node => this.node = node}
                 width={this.state.size[0]} height={this.state.size[1]}>
            </svg>
        </div>
    }
}

BarChart.propTypes = {
};

module.exports = BarChart;