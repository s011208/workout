package bj4.yhh.workout.data;
import bj4.yhh.workout.data.TrainData;

interface IDataService {
  TrainData[] getAllTrainData();
  void addTrainData(in TrainData data);
}