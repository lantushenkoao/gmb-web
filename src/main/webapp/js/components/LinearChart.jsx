import React, {Component} from 'react';
import _ from 'lodash';

import { scaleLinear, scaleOrdinal, scaleTime } from 'd3-scale';
import { extent } from 'd3-array';
import { select, selectAll } from 'd3-selection';
import { axisLeft, axisBottom } from 'd3-axis';
import { timeFormat } from 'd3-time-format';

//D3 tutorial https://bost.ocks.org/mike/bar/3/
//https://bl.ocks.org/mbostock/3885304
class LinearChart extends Component {

    constructor(props) {
        super(props);
        this.mounted = false;
        this.shouldUpdate = true;
    }

    componentWillReceiveProps(nextProps) {
        if (!_.isEqual(nextProps.data, this.props.data)) {
            this.shouldUpdate = true;
            return;
        }
        this.shouldUpdate = false;
    }

    componentDidMount(){
        this.renderChart();
        this.mounted = true;
    }

    componentDidUpdate(){
        if(this.mounted && this.shouldUpdate) {
            this.renderChart();
        }
    }


    renderChart(){
        console.log('rendering chart');
        const node = this.node;

        selectAll("svg > *").remove();

        const yExtent = extent(this.props.data, d=>d.data);
        const xExtent = extent(this.props.data, d=>new Date(d.bucketStart));
        const margin = {top: 20, right: 30, bottom: 30, left: 80},
            width = this.props.size[0] - margin.left - margin.right,
            height = this.props.size[1] - margin.top - margin.bottom;

        const chart = select(node).append('g')
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        const barPadding = 1.02;
        const barWidth = width / (this.props.data.length * barPadding );



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
            .data(this.props.data)
            .enter()
            .append('rect')
            .attr('class', 'bar')
            .attr('x', (d,i) => xScale(new Date(d.bucketStart)))
            .attr('y', d => height - yScale(d.data))
            .attr('height', d => yScale(d.data))
            .attr('width', barWidth);
    }

    render(){
        return <div className="chart-container">
            <svg ref={node => this.node = node}
                 width={this.props.size[0]} height={this.props.size[1]}>
            </svg>
        </div>
    }
}

LinearChart.propTypes = {
    size: React.PropTypes.array,
    data: React.PropTypes.array
}

module.exports = LinearChart;