import React, {Component} from 'react';
import Header from '../../components/Header.jsx';
import ParametersSelector from '../../components/ParametersSelector.jsx';
import moment from 'moment';

import { scaleLinear, scaleOrdinal, scaleTime } from 'd3-scale';
import { extent } from 'd3-array';
import { select, selectAll } from 'd3-selection';
import { axisLeft, axisBottom } from 'd3-axis';
import { timeFormat } from 'd3-time-format';
import { format } from 'd3-format';




//D3 tutorial https://bost.ocks.org/mike/bar/3/
//https://bl.ocks.org/mbostock/3885304
class BarChart extends Component {

    constructor(props){
        super(props);
        this.state = {
            stations : [],
            selectedStations : {
                code : 4553270,
                id: '1',
                name: '4553270 Черноморское'
            },
            fields: [],
            selectedFields: [],
            startDate: new Date(1950, 1, 1),
            endDate: new Date(1950, 3, 1),
            data: [],
            bucketsCount: 50

        };
        this.createBarChart = this.createBarChart.bind(this);
        this.renderBarChart = this.renderBarChart.bind(this);
        this.onParametersChanged = this.onParametersChanged.bind(this);
    }

    onParametersChanged(name, value){
        const obj = {};
        obj[name] = value;
        this.setState(obj);
    }

    createBarChart() {
        $.ajax({
            type: 'GET',
            cache: false,
            url: '/api/data/aggregated/avg',
            data: {
                periodStart: moment(this.state.startDate).format('YYYY-MM-DD'),
                periodEnd: moment(this.state.endDate).format('YYYY-MM-DD'),
                tableName: this.state.selectedFields.map(f=>f.tableName)[0],
                columnName: this.state.selectedFields.map(f=>f.columnName)[0],
                bucketsCount: this.state.bucketsCount,
                stationCodes: this.state.selectedStations.code
            }
        }).done(data=>{
            this.setState({
                data: data
            }, this.renderBarChart);
        });
    }

    renderBarChart() {
        const node = this.node;

        selectAll("svg > *").remove();

        const yExtent = extent(this.state.data, d=>d.data);
        const xExtent = extent(this.state.data, d=>new Date(d.bucketStart));
        const margin = {top: 20, right: 30, bottom: 30, left: 80},
            width = this.props.size[0] - margin.left - margin.right,
            height = this.props.size[1] - margin.top - margin.bottom;

        const chart = select(node).append('g')
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        const barPadding = 1.02;
        const barWidth = width / (this.state.data.length * barPadding );



        const yScale = scaleLinear()
            .domain(yExtent)
            .range([0, height]);

        const xScale = scaleTime()
            .domain(xExtent)
            .range([0, width]);


        const xAxis = axisBottom(xScale).tickFormat(timeFormat("%Y-%m-%d"));
        const yAxis = axisLeft(yScale)
            .ticks(10);

        chart.append('g')
            .attr('class', 'x axis')
            .attr('transform', 'translate(0,' + height + ')')
            .call(xAxis);

        chart.append("g")
            .attr("class", "y axis")
            .call(yAxis);

        chart
            .selectAll('rect')
            .data(this.state.data)
            .enter()
                .append('rect')
                .attr('class', 'bar')
                .attr('x', (d,i) => xScale(new Date(d.bucketStart)))
                .attr('y', d => height - yScale(d.data))
                .attr('height', d => yScale(d.data))
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
                        name: `${d.code} ${d.name}`,
                        code: d.code
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
                       name: `${d.description} (${d.tableName} - ${d.columnName})'`,
                       tableName: d.tableName,
                       columnName: d.columnName
                   };
                })
            });
        });
    }

    render(){
        return <div>
            <Header/>
            <ParametersSelector
                onParameterChanged={this.onParametersChanged}
                stations={this.state.stations}
                selectedStations={this.state.selectedStations}
                fields={this.state.fields}
                selectedFields={this.state.selectedFields}
                startDate={this.state.startDate}
                endDate={this.state.endDate}
                bucketsCount={this.state.bucketsCount}
            />
            <div className="row">
                <div className="col-lg-4">
                    <button onClick={this.createBarChart}>Показать</button>
                </div>
            </div>
            <div className="chart-container">
                <svg ref={node => this.node = node}
                     width={this.props.size[0]} height={this.props.size[1]}>
                </svg>
            </div>
        </div>
    }
}

BarChart.defaultProps = {
    size: [1000,500]
}

BarChart.propTypes = {
    size: React.PropTypes.array
};

module.exports = BarChart;